var playerPos=false;
var oldPos = 10;		  //Ŭ���� ���콺��ǥ
var oldPixel = 10;		//Ŭ���� �����̴� ��ǥ
var bDrag = false;		//������
var bPlay = false;		//�÷�����
var all_end_time=0;
var old_xpos=0;

var stime=-1;
var etime=-1;
var eloop=false;

function getCookie(name)
{
	var sp = document.cookie.indexOf(name + "=");
	if (sp == -1)
		return null;
	var ep = document.cookie.indexOf(";", sp);
	if (ep == -1)
		ep = document.cookie.length;
	return document.cookie.substr(sp, ep - sp).split('=')[1];
}

function addBookmark(ptime)
{
	opt = new Option();
	opt.text = TimeFormat(ptime);
	opt.value = ptime;

	obj = document.getElementById("bookmark_time");
	obj.options[obj.length] = opt;

	opt = obj.options[0];
	opt.value = ptime;
	opt.text = "����(" + (obj.length-1) + ")";
	opt.selected = true;
}

function getBookmarkId()
{
	var player = document.getElementById(def_activex_name);
	return escape("BM:" + player.getBookmarkID());
}

function loadBookmark()
{
	var bookmarks = getCookie(getBookmarkId());
	if (!bookmarks)
		return;
	var timeList = bookmarks.split(':');
	for (var i = 0; i < timeList.length; ++i)
		addBookmark(timeList[i]);
}

function saveBookmark()
{
	var timeList = new Array;
	var obj = document.getElementById("bookmark_time");
	for (var i = 1; i < obj.options.length; ++i)
		timeList.push(obj.options[i].value);
	var expiredDate = new Date;
	expiredDate.setMonth(expiredDate.getMonth() + 1);
	document.cookie = getBookmarkId() + "=" + timeList.join(':') + "; expires=" + expiredDate.toGMTString();
	//alert(document.cookie);
}

function BookmarkAdd()
{
	player = document.getElementById(def_activex_name);
 	ptime = player.GetCurrentPosition();
	addBookmark(ptime);

	saveBookmark();
}

function BookmarkMove()
{
	player = document.getElementById(def_activex_name);

	obj = document.getElementById("bookmark_time");
	opt = obj.options[obj.selectedIndex];
	if (opt.value)
		player.SetCurrentPosition(opt.value);
}

function BookmarkDelete()
{
	obj = document.getElementById("bookmark_time");
	if (obj.length <= 1) return;

	obj.options[obj.selectedIndex] = null;
	opt = obj.options[0];
	opt.text = "����(" + (obj.length-1) + ")";
	opt.selected = true;

	saveBookmark();
}

function repeatcontrol_onmouse(obj, img)
{
	if (img == null) {
		if (eloop)
		{
			obj.src = def_btn_repeat_down;
		} else {
			obj.src = def_btn_repeat_normal;
		}
	} else {
		obj.old = obj.src;
		obj.src = img;
	}
}

function button_onmouse(obj, img)
{
	if (img == null) {
		if (obj.old)
			obj.src = obj.old;
	} else {
		obj.old = obj.src;
		obj.src = img;
	}
}
/********************************************************************
��� ����
********************************************************************/
function play_back(smode) 
{
	player = document.getElementById(def_activex_name);
 	stime = player.GetCurrentPosition();
	if (smode == 1)
	{
		stime = stime + (config_backward_seconds);
		if (stime < 0)
			stime = 0;
		player.SetCurrentPosition(stime);
	}
	else
	{
		stime = stime + (config_forward_seconds);
		if (stime < 0)
			stime = 0;
		player.SetCurrentPosition(stime);
	}
}

function play_repeat(smode) 
{
	if (!bPlay)
	 return;

	player = document.getElementById(def_activex_name);

	switch (smode)
	{
			case 0:  //  START SAVE
				stime = player.GetCurrentPosition();
				document.images['Image1'].src = def_img_btn_repeat_begin_active;
				alert('�������� �ð��� �����߽��ϴ�.\n\n�������� �ð����� �������� ��ư�� ��������!!');
			break;
			case 1:  //  START END
				if (stime<0)
				{
					alert('�������� �ð��� �����ϴ�.\n\n����, �������� ��ư�� Ŭ���ϼ���!!');
					return;
				}
				etime = player.GetCurrentPosition();
				document.images['Image2'].src = def_img_btn_repeat_end_active;
				alert('�������� �ð��� �����߽��ϴ�.\n\n�����ݺ��� �����ø�, �����ݺ��� ���۵˴ϴ�.\n\n�����ݺ� ������ �������� ��ư�� ��������!!');
			break;
			case 2:  //  START LOOP
				if (etime<stime)
				{
					alert('�������� �ð��� �������� �ð����� Ů�ϴ�!!\n\n�ٽ� ������ �ּ���!!');
					stime=-1;
					etime=-1;
					return;
				}
				if (etime==-1 && stime==-1)
				{
					alert('���� ����/���� �ð��� �����ϴ�!!\n\n�ٽ� ������ �ּ���!!');
					stime=-1;
					etime=-1;
					return;
				}
				document.images['Image3'].src = def_img_btn_repeat_repeat_active;
				player.SetCurrentPosition(stime);
				eloop=true;
			break;
			case 3:  //  START DEL
				stime=-1;
				etime=-1;
				eloop=false;
				document.images['Image1'].src = def_img_btn_repeat_begin;
				document.images['Image2'].src = def_img_btn_repeat_end;
				document.images['Image3'].src = def_img_btn_repeat_repeat;
				alert('�����ݺ��� �����մϴ�!!');
			break;
	}
}

var progressBar_StartPixel = 0;		//�÷��̹� ������ġ
//var progressBar_Size = def_progressbar_size;		//�÷��̹� ũ��

function Pixel2Pos(nPixel)
{
	player = document.getElementById(def_activex_name);
	return parseInt((nPixel) * player.GetDuration() / def_progressbar_size);
}

function progressBg_onmousedown() 
{
	if (!bPlay)
		return ;
		
	player = document.getElementById(def_activex_name);

	if (player.GetCurrentPosition()== 0)
		return false;
	
	var XPos = event.clientX;
	if((progressBar_StartPixel <= XPos) && (XPos <= progressBar_StartPixel + (def_progressbar_size)))	
	{
		document.all.progress_seekball.style.pixelLeft = XPos;
		player.SetCurrentPosition(Pixel2Pos(progress_seekball.style.pixelLeft - progressBar_StartPixel)); 
	}	
}

function progressBar_onmousedown() 
{
	if (!bPlay)
		return ;
		
	player = document.getElementById(def_activex_name);
	if (player.GetCurrentPosition()== 0)
		return false;

	oldPos = event.clientX;
	bDrag  = true;
	TrackBar = event.srcElement.parentElement;
	oldPixel = progress_seekball.style.pixelLeft; 
	document.onmousemove = PlayMoveSlider;
	if(document.all)
	{
		document.onmouseup = PlayStopSlider;
	}
}



function PlayMoveSlider() 
{
	if (bDrag) 
	{
		var XPos = oldPixel + (event.clientX - oldPos);	//���� ���콺�ٿ��϶� ��ǥ���� mousemove�� ��ǥ��

		if (eloop)
		{
			obj = document.getElementById("repeat_block_begin");
			begin_pos = obj.style.pixelLeft;
			obj = document.getElementById("repeat_block_end");
			end_pos = obj.style.pixelLeft;

			if (XPos < begin_pos)
				XPos = begin_pos;
			if (XPos > end_pos)
				XPos = end_pos;
		}

		if((progressBar_StartPixel <= XPos) && (XPos <= progressBar_StartPixel + (def_progressbar_size)))	
		{
			//���α׷����� �̵�
			document.all.progress_seekball.style.pixelLeft = XPos;
			obj = document.getElementById("played_block");
			obj.style.width = XPos - obj.style.left;
			if (XPos!=old_xpos)
			{
				obj = document.getElementById("playtime");
				obj.innerHTML=TimeFormat(Pixel2Pos(progress_seekball.style.pixelLeft - progressBar_StartPixel))+" / "+TimeFormat(all_end_time);
				old_xpos=XPos;
			}
			//
		}
		return false;
	}
}


function PlayStopSlider() 
{
	bDrag = false;
	player = document.getElementById(def_activex_name);
	player.SetCurrentPosition(Pixel2Pos(progress_seekball.style.pixelLeft - progressBar_StartPixel)); 
	
	if (player.GetPlayState() == 2)	//�Ͻ� ���� �϶�, �� �����̴� �����ӿ� ���� �ߴܵǾ��� ����, �ٽ� ����
		 player.Play();		
		
	document.onmousemove = null;
	if(document.all);
		document.onmouseup = null;
}	

/**
 * �����ݺ�
 */
var clicked_object = null;
var sectionRepeatInitialized = false;

function SectionRepeatEnable()
{
	player = document.getElementById(def_activex_name);
	if (eloop == false)
	{
		eloop = true;
		if (!sectionRepeatInitialized)
		{
			obj = document.getElementById("repeat_block_begin");
			obj.init_left = obj.style.pixelLeft;

			obj = document.getElementById("repeat_block_end");
			obj.init_left = obj.style.pixelLeft + def_progressbar_size;
			sectionRepeatInitialized = true;
		}
		stime = 0;
		etime = player.GetDuration();

		obj = document.getElementById("repeat_block_begin");
		obj.style.pixelLeft = obj.init_left;

		obj = document.getElementById("repeat_block_end");
		obj.style.pixelLeft = obj.init_left;

		obj = document.getElementById("button_repeat");
		obj.src = def_btn_repeat_down;
		obj = document.getElementById("repeat_block_begin");
		obj.style.display = "block";
		obj = document.getElementById("repeat_block_end");
		obj.style.display = "block";

		obj = document.getElementById("played_block");
		obj.style.display = "none";

		obj = document.getElementById("repeat_block");
		obj.style.display = "block";

		PaintRepeatBlock();

		obj = document.getElementById("play_track");
		obj.onclick = null;
//		alert("�����ݺ� ����� ����մϴ�.");
	} else {
		eloop = false;
		obj = document.getElementById("button_repeat");
		obj.src = def_btn_repeat_normal;
		obj = document.getElementById("repeat_block_begin");
		obj.style.display = "none";
		obj = document.getElementById("repeat_block_end");
		obj.style.display = "none";

		obj = document.getElementById("played_block");
		obj.style.display = "block";

		obj = document.getElementById("repeat_block");
		obj.style.display = "none";

		obj = document.getElementById("play_track");
		obj.onclick = ChangeTrackPosition;
//		alert("�����ݺ� ����� ����ϴ�.");
	}
}

function getAbsPos(obj)
{
	left_pos = 0;

	while(obj != null)
	{
		left_pos += obj.offsetLeft;
		obj = obj.offsetParent;
	}

	return left_pos;
}

function ChangeTrackPosition()
{
	obj = document.getElementById("play_track");
	XPos = event.clientX - getAbsPos(obj) - 4;

	document.all.progress_seekball.style.pixelLeft = XPos;
	player = document.getElementById(def_activex_name);
	player.SetCurrentPosition(Pixel2Pos(XPos - progressBar_StartPixel)); 
}

function ControlHandle_onmousedown(obj) 
{
	if (!bPlay)
		return ;
		
	clicked_object = obj;

	oldPos = event.clientX;							//���� ���콺�ٿ��϶� ��ǥ
	oldPixel = obj.style.pixelLeft;				//���� ���콺�ٿ� �϶� �����̴���ǥ

	bDrag = true;
	document.onmousemove = ControlHandleSlider;			//onmousemoveĸ��
	if (document.all)
		document.onmouseup = ControlHandleStop;		//onmousemove ����
}

function PaintRepeatBlock()
{
	begin_obj = document.getElementById("repeat_block_begin");
	end_obj = document.getElementById("repeat_block_end");
	obj = document.getElementById("repeat_block");
	obj.style.pixelLeft = begin_obj.style.pixelLeft;
	obj.style.width = end_obj.style.pixelLeft - begin_obj.style.pixelLeft;
}

function ControlHandleSlider() 
{
	if (bDrag) 
	{
			

		obj = document.getElementById("progress_seekball");
		var seekball_pos = obj.style.pixelLeft + oldPos - oldPixel;

		if (clicked_object.id == "repeat_block_begin") 
		{
			if (event.clientX > seekball_pos)
				clientX = seekball_pos;
			else
				clientX = event.clientX;
		} else {
			if (event.clientX < seekball_pos)
				clientX = seekball_pos;
			else
				clientX = event.clientX;
		}

		var XPos = oldPixel + clientX - oldPos;	//���� ���콺�ٿ��϶� ��ǥ���� mousemove�� ��ǥ��
	
		if ((progressBar_StartPixel <= XPos) && (XPos <= progressBar_StartPixel + def_progressbar_size))	
		{
			clicked_object.style.pixelLeft = XPos;	//���콺 �̵��� ��ŭ �����̴� �̵�
			if (clicked_object.id == "repeat_block_begin")
			{
				stime = Pixel2Pos(XPos); 
			} else {
				etime = Pixel2Pos(XPos); 
			}

			PaintRepeatBlock();
		}
		return false;
	}
}

function ControlHandleStop() 
{
	bDrag = false;
}


/*********************************************************************
volume����
**********************************************************************/

var VolumeBar_StartPixel = 0;
var VolumeBar_Size = def_volumebar_size;	//������ ũ��
var MediaTimer=null;
var MediaDown=null;

function VolumeBar_onmousedown() 
{
	if (!bPlay)
		return ;
		
	oldPos = event.clientX;							//���� ���콺�ٿ��϶� ��ǥ
	oldPixel = volume_adjuster.style.pixelLeft;				//���� ���콺�ٿ� �϶� �����̴���ǥ

	bDrag = true;
	document.onmousemove = VolumeMoveSlider;			//onmousemoveĸ��
	if (document.all)
		document.onmouseup=VolumeStopSlider;		//onmousemove ����
}

function VolumeAdjust(value)
{
	player = document.getElementById(def_activex_name);
	player.SetVolume( player.GetVolume() + (value) );
	VolumeInit();
	PaintCurrentVolume();
}

function PaintCurrentVolume()
{
	adjuster_obj = document.getElementById("volume_adjuster");
	adjuster_obj.style.pixelLeft;

	current_obj = document.getElementById("volume_current");
	current_obj.style.width = adjuster_obj.style.pixelLeft - current_obj.style.pixelLeft;
}

function VolumeMoveSlider() 
{
	player = document.getElementById(def_activex_name);
	if (bDrag) 
	{
		var XPos = oldPixel + event.clientX - oldPos;	//���� ���콺�ٿ��϶� ��ǥ���� mousemove�� ��ǥ��
		
		if ((VolumeBar_StartPixel <= XPos) && (XPos <= VolumeBar_StartPixel + VolumeBar_Size))	
		{
			volume_adjuster.style.pixelLeft = XPos;	//���콺 �̵��� ��ŭ �����̴� �̵�
			player.SetVolume((XPos - VolumeBar_StartPixel) / VolumeBar_Size * 100);
			PaintCurrentVolume();
		}
		return false;
	}//if (bDrag)
}

function VolumeStopSlider() 
{
	bDrag = false;
}

function VolumeInit() 
{
	//�����ʱ�ȭ
	player = document.getElementById(def_activex_name);
	volume_adjuster.style.pixelLeft = player.GetVolume() / 100 * VolumeBar_Size + VolumeBar_StartPixel;
}

//���Ұ�  ---------------------------------------------------------------------------------------
function SoundVolumeMute() 
{

	if (!bPlay)
		return ;

	player = document.getElementById(def_activex_name);
	obj = document.getElementById("button_speaker");
		
	if(player.GetMute()==false)
	{
		obj.src = def_btn_speaker_down;
		player.SetMute(true)
	}
	else
	{
		obj.src = def_btn_speaker_normal;
		player.SetMute(false)
	}
}

function playerColor(Color)
{

	if (Color == "ImgPlay") {
		document.images['ImgPlay'].src = def_img_btn_play_active;
	} else {
		document.images['ImgPlay'].src = def_img_btn_play;
	}
	if (Color == "ImgStop") {
		document.images['ImgStop'].src = def_img_btn_stop_active;
	} else {
		document.images['ImgStop'].src = def_img_btn_stop;
	}
	if (Color == "ImgNext") {
		document.images['ImgNext'].src = def_img_btn_forward_active;
	} else {
		document.images['ImgNext'].src = def_img_btn_forward;
	}
	if (Color == "ImgBack") {
		document.images['ImgBack'].src = def_img_btn_backward_active;
	} else {
		document.images['ImgBack'].src = def_img_btn_backward;
	}
	if (Color == "ImgPause") {
		document.images['ImgPause'].src = def_img_btn_pause_active;
	} else {
		document.images['ImgPause'].src = def_img_btn_pause;
	}
}

function speedcontrol_onmouse(obj, img)
{
	if (img == null) {
		if (obj.old)
			obj.src = obj.old;
	} else {
		obj.old = obj.src;
		obj.src = img;
	}
}

function speedColor(Color)
{
	if (config_lowspeed_enabled)
	{
		if (Color == "0.6") {
			document.images['speed0_6'].src = def_btn_x06_down;
		} else {
			document.images['speed0_6'].src = def_btn_x06_normal;
		}
		if (Color == "0.8") {
			document.images['speed0_8'].src = def_btn_x08_down;
		} else {
			document.images['speed0_8'].src = def_btn_x08_normal;
		}
		if (Color == "1") {
			document.images['speed1e'].src = def_btn_x1e_down;
		} else {
			document.images['speed1e'].src = def_btn_x1e_normal;
		}
	} else {
		if (Color == "1") {
			document.images['speed1'].src = def_btn_x1_down;
		} else {
			document.images['speed1'].src = def_btn_x1_normal;
		}
	}
	if (Color == "1.2") {
		document.images['speed1_2'].src = def_btn_x12_down;
	} else {
		document.images['speed1_2'].src = def_btn_x12_normal;
	}
	if (Color == "1.4") {
		document.images['speed1_4'].src = def_btn_x14_down;
	} else {
		document.images['speed1_4'].src = def_btn_x14_normal;
	}
	if (Color == "1.6") {
		document.images['speed1_6'].src = def_btn_x16_down;
	} else {
		document.images['speed1_6'].src = def_btn_x16_normal;
	}
	if (Color == "1.8") {
		document.images['speed1_8'].src = def_btn_x18_down;
	} else {
		document.images['speed1_8'].src = def_btn_x18_normal;
	}
	if (Color == "2") {
		document.images['speed2'].src = def_btn_x2_down;
	} else {
		document.images['speed2'].src = def_btn_x2_normal;
	}
}

function ImgChange(ImgName,ImgSrc)
{
	document.images[ImgName].src = ImgSrc;
}

function playerControl(action)
{	
	player = document.getElementById(def_activex_name);
	if (action == "play" || (action == "open"))
	{
		player.Play();
		bPlay = true;
		//playerColor("ImgPlay");
	}
	else if (action == "pause")
	{		
		player.Pause();
		//playerColor("ImgPause");
		
	}
	else if (action == "open")
	{
			//��������	
			VolumeInit();
	}
	else if (action == "stop")
	{	
		player.Stop();
		player.SetCurrentPosition(0);
		//playerColor("ImgStop");
		//��������	
		VolumeInit();
	}
	else if (action == "full")
	{		
			player.FullScreen();
	}
}
	

//------------------------------------------------------
// ������ ��� ��� ����
//------------------------------------------------------
function player_setRate(rate)
{
	player = document.getElementById(def_activex_name);
	if(rate==1)
		speedColor("1");
	else if(rate==1.2)
		speedColor("1.2");
	else if(rate==1.4)
		speedColor("1.4");
	else if(rate==1.6)
		speedColor("1.6");
	else if(rate==1.8)
		speedColor("1.8");
	else if(rate==2)
		speedColor("2");
	else if(rate==0.6)
		speedColor("0.6");
	else if(rate==0.8)
		speedColor("0.8");


	player.SetRate(rate);
}

var currRate = 1;
var currImag = null;

/*
function fPlayer_setRate(rate)
{
		//�ӵ� ����
		// set rate
		player_setRate(rate);
}
*/

//�÷��� Ʈ���� �ڵ��̵�-----------------------------------------------------------------------------------
function ScrollBarState() 
{
	bPlay = true;

	player = document.getElementById(def_activex_name);

	if (eloop==true && player.GetCurrentPosition()>=etime)
	{
		player.SetCurrentPosition(stime);
	}
	
	if(bDrag == false)
	{
		all_end_time = player.GetDuration();
		obj = document.getElementById("playtime");
		obj.innerHTML = TimeFormat(player.GetCurrentPosition())+" / "+ TimeFormat(all_end_time);

		progress_seekball.style.pixelLeft = progressBar_StartPixel + parseInt(player.GetCurrentPosition() * def_progressbar_size / player.GetDuration());

		obj = document.getElementById("played_block");
		obj.style.width = progress_seekball.style.pixelLeft - obj.style.left;

	}
	
	if(progress_seekball.style.pixelLeft == 0) 
	{
		progress_seekball.style.pixelLeft = progressBar_StartPixel;
	}
}




/**
 * �̺�Ʈ ó��
 */

var MediaDown=null;
function TimeFormat(totalsecond)
{
	var second = parseInt(totalsecond) % 60;
	var minute = parseInt(totalsecond / 60);
	var hour = parseInt(minute / 60);
	return ((minute < 10)?"0":"")+minute+":" + ((second < 10)?"0":"")+second;
}

function MediaPlayer_DoOpenStateChange(state)
{
	if (state == 13)
		loadBookmark();
	//OpenState.innerHTML = "OpenState : " + state;
}

function MediaPlayer_DoPlayStateChange(state)
{
	//PlayState.innerHTML = "PlayState : " + state;
	switch (state)
	{
	case 1: //STOP
			if (MediaTimer!=null) 
			{
				/*
					try
					{
						clearTimeout(MediaTimer);
						MediaTimer = null;
						progressBar.style.pixelLeft = progressBar_StartPixel;
					}
					catch(e)
					{
						return;
					}
					*/
			}
			obj = document.getElementById("playstatus_text");
			obj.src = def_txt_stop;
			//bPlay = true;
			//ing.innerHTML="����";
			//obj = document.GetElementById("btnStop");
			//obj.src = "img/btn_2_up.gif";
			obj = document.getElementById("button_pause");
			obj.style.display = "none";
			obj = document.getElementById("button_play");
			obj.style.display = "block";
		break;
		case 2:  // PAUSE
			if (MediaTimer!=null) 
			{
			//	try{clearTimeout(MediaTimer);MediaTimer=null;}catch(e){return;}
			}
			//bPlay = true;
			//ing.innerHTML="�Ͻ�����";

			obj = document.getElementById("playstatus_text");
			obj.src = def_txt_pause;

			obj = document.getElementById("button_pause");
			obj.style.display = "none";
			obj = document.getElementById("button_play");
			obj.style.display = "block";
		break;
		
		case 3:	 // PLAY
			if (MediaTimer==null) 
			{
					MediaTimer = window.setInterval("ScrollBarState()", 500);			
					
					player_setRate('1.0');
					speedColor("1");
					VolumeInit();
					//playerColor("ImgPlay");
					
			}
			//ing.innerHTML="�����";
			obj = document.getElementById("playstatus_text");
			obj.src = def_txt_playing;

			obj = document.getElementById("button_pause");
			obj.style.display = "block";
			obj = document.getElementById("button_play");
			obj.style.display = "none";
			break;
		
	}
}

function MediaPlayer_DoBuffering(buffering)
{
	obj = document.getElementById("playstatus_text");
	obj.src = def_txt_buffering;
}

function MediaPlayer_DoPositionChange(oldPos, newPos)
{
    //PositionChange.innerHTML = "OldPos : " + oldPos + " NewPos : " + newPos;
}

var callSetRate = false;
function AxPlayer_RateChange(rate)
{
	if (callSetRate)
		return;
	callSetRate = true;
	player_setRate(rate.toFixed(1));
	callSetRate = false;
	
	//toggleSpeed(rate.toFixed(1));
}

function PaintSoundVolumeMute()
{
	if (!bPlay)
		return ;

	player = document.getElementById(def_activex_name);
	obj = document.getElementById("button_speaker");
		
	if(player.GetMute())
	{
		obj.src = def_btn_speaker_down;
	} else {
		obj.src = def_btn_speaker_normal;
	}
}

function AxPlayer_VolumeChange(volume, mute)
{
	PaintCurrentVolume();
	VolumeInit();
	PaintSoundVolumeMute();
}