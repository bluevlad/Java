/**
* ���ϸ�: lib.paging.js
* ��  ��: �������� ����ϱ�
* �ۼ���: ��ģ����
* ��  ¥: 2004-02-04
*
***********************************************
* 2004-02-05 ��ó��, �Ǹ����� ������ ��ũ �߰��ϰ�, ������ �Ⱥ����� �Ӽ��� ������ �� �ֵ��� ���� - by ��ģ����
* 2004-02-05 QueryString ������Ʈ ���� - by lainTT
* 2004-03-?? ������ �ѹ��� ��Ÿ���� ������ �� �ֵ��� ���� ��������ȣ= %n
*/

QueryString = function(str) {
	var str = str ? str : document.location.href;
	this.argv = new Array();
	this.queryString = str.replace(/^[^\?]+\?/, '').replace(/#(.*)$/, '');
	if (!this.queryString) this.queryString = '';
	var _argv = this.queryString.split('&');
	for(var i=0; i<_argv.length; i++) {
		var _key = _argv[i].substring(0, _argv[i].indexOf('='));
		var _val = _argv[i].substring(_argv[i].indexOf('=')+1);
		if(!_key || _argv[i].indexOf('=') == -1) continue;
		this.argv[_key] = _val;
	}
    alert(str);
}

QueryString.prototype.setVar = function(key,val) {
	if (typeof key == 'object') {
		for (var item in key) this.argv[item] = key[item];
	} else {
		this.argv[key] = val;
	}
	return this.getVar();
}

QueryString.prototype.getVar = function(key) {
	if (key) {
		return this.argv[key] ? this.argv[key] : '';
	} else {
		var _item = new Array();
		for (var x in this.argv) {
			if (this.argv[x]) _item[_item.length] = x + '=' + this.argv[x];
			else continue;
		}
		return '/?' + _item.join('&');
	}
}

Paging = function(total) {
	this.config = {
		pageVariable: 'v_page',
		numberFormat: '[%n]',
		showFirstLast: true,	// ��ó��, �� ���������� ���� ��ũ�� ������ΰ�.
		thisPageStyle: 'font-weight: bold;',
		itemPerPage: 10,	// ����Ʈ ��ϼ�
		pagePerView: 10,	// �������� �׺���̼� �׸��
		prevIcon: null,	// ���������� ������
		nextIcon: null,	// ���������� ������
		firstIcon: null,	// ù�������� ������
		lastIcon: null	// ������������ ������
	}

	this.totalItem = total;
	this.qs = new QueryString;

	this.calculate = function() {
		this.totalPage = Math.ceil(this.totalItem / this.config.itemPerPage);
		this.currentPage = this.qs.getVar(this.config.pageVariable);
		if (!this.currentPage) this.currentPage = 1;
		if (this.currentPage > this.totalPage) this.currentPage = this.totalPage;
		this.lastPageItems = this.totalPage % this.config.itemPerPage;

		this.prevPage = this.currentPage-1;
		this.nextPage = this.currentPage+1;
		this.seek = this.prevPage * this.config.itemPerPage;
		this.currentScale = parseInt(this.currentPage / this.config.pagePerView);
		if (this.currentPage % this.config.pagePerView < 1) this.currentScale--;
		this.totalScale = parseInt(this.totalPage / this.config.pagePerView);
		this.lastScalePages = this.totalPage % this.config.pagePerView;
		if (this.lastScalePages == 0) this.totalScale--;
		this.prevPage = this.currentScale * this.config.pagePerView;
		this.nextPage = this.prevPage + this.config.pagePerView + 1;
	}

	this.toString = function() {
		var ss, se;
		var firstBtn = '';
		var lastBtn = '';
		var prevBtn = '';
		var nextBtn = '';

		this.calculate();

		if (this.config.showFirstLast) {
			if (this.config.firstIcon) firstBtn = '<img src="'+this.config.firstIcon+'" border="0" align="absmiddle">';
			else firstBtn = '[ó��]'; //'��';
			firstBtn = firstBtn.link(this.qs.setVar(this.config.pageVariable,1));

			if (this.config.lastIcon) lastBtn = '<img src="'+this.config.lastIcon+'" border="0" align="absmiddle">';
			else lastBtn = '[������]'; //'��';
			lastBtn = lastBtn.link(this.qs.setVar(this.config.pageVariable,this.totalPage));
		} else {
			firstBtn = lastBtn = '';
		}

		if (this.config.prevIcon) prevBtn ='<img src="'+this.config.prevIcon+'" border="0" align="absmiddle">';
		else prevBtn = '��';
		if (this.currentPage > this.config.pagePerView) {
			prevBtn = prevBtn.link(this.qs.setVar(this.config.pageVariable,this.prevPage));
		}

		ss = this.prevPage + 1;
		if ((this.currentScale >= this.totalScale) && (this.lastScalePages != 0)) se = ss + this.lastScalePages;
		else if (this.currentScale <= -1) se = ss;
		else se = ss + this.config.pagePerView;

		var navBtn = '';
		for(var i = ss; i<se; i++) {
			var pageText = this.config.numberFormat.replace(/%n/g,i);
			if (i == this.currentPage) {
				_btn = '<span style="'+this.config.thisPageStyle+'">'+pageText+'</span>';
			} else {
				_btn = '<a href="'+this.qs.setVar(this.config.pageVariable,i)+'" style="'+this.config.otherPageStyle+'">'+pageText+'</a>'
			}
			navBtn+=_btn;
		}

		if (this.config.prevIcon) nextBtn ='<img src="'+this.config.nextIcon+'" border="0" align="absmiddle">';
		else nextBtn = '��';
		if (this.totalPage > this.nextPage) {
			nextBtn = nextBtn.link(this.qs.setVar(this.config.pageVariable,this.nextPage));
		}
		return firstBtn+' '+prevBtn+navBtn+nextBtn+' '+lastBtn;
	}
}