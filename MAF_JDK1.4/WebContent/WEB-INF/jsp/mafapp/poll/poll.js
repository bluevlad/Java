// ��� ���� �ڵ尡 �Ʊ����... ������
// 2006�� 10�� ����� naver blog ���� 
<script>
	function PollItem(seq, question,dml) {
		this.seq = seq;
		this.question = question;
		this.dml = "";
	}
	
	function Poll() {
		this.items = new Array();
	}
	
	Poll.prototype.addItem = function(seq, question, dml) {
		this.items[this.items.length] = new PollItem(seq, question,dml);
	}
	
	Poll.prototype.getItem = function(seq) {
		for( i =0; i < this.items.length; i++ ) {
			if(this.items[i].seq == seq) {
				return this.items[i];
			}
		}
		return null;
	}
	Poll.prototype.getMaxSeq=function() {
		var seq = 0;
		for( i =0; i < this.items.length; i++ ) {
			if(this.items[i].seq > seq) {
				seq = this.items[i].seq;
			}
		}
		return seq;
	}	
	Poll.prototype.drawOption= function() {
		var frm = getObject("frmItemEdit");
		if( frm ) {
			var tgtOption = frm.elements["selView"];
			tgtOption.length = 0;
			for( i =0; i < this.items.length; i++ ) {
				var option2 = new Option(this.items[i].question, this.items[i].seq);
				//option2.onclick=itemEdit( this.items[i].seq );
		        var sl = tgtOption.options.length;
		        tgtOption.options[sl] = option2;
			}
			
		}
	}
	var mPoll = new Poll();
	 <c:forEach var="item" items="${pollitems}" varStatus="status">
		mPoll.addItem(${item.seq},"<c:out value="${item.question}" escapeXml="true"/>");
	  </c:forEach>

  	function itemEdit(objSel) {
		var seq = objSel.value;
		
		var item = mPoll.getItem(seq);
		if( item ) {
			var frm = getObject("frmItemEdit");
			if(frm) {
				frm.seq.value = item.seq;
				frm.question.value = item.question;
			} else {
				alert("form not found");
			}
		} else {
			alert ( "item not found");
		}
	}
	
	function itemAdd() {
		var frm = getObject("frmItemEdit");
		if(frm) {
			var tgtOption = frm.elements["selView"];
			var maxSeq = mPoll.getMaxSeq()+1;
			var option2 = new Option(maxSeq+"����", maxSeq);
			mPoll.addItem(maxSeq,maxSeq + "����","A");
			for(i = 0; i < tgtOption.options.length; i++ ) {
				tgtOption.options[i].selected=false;
			}
	        var sl = tgtOption.options.length;
	        tgtOption.options[sl] = option2;
			tgtOption.options[sl].selected=true;
			itemEdit(tgtOption.options[sl]);
		}
	}
	function itemDelete() {
		var frm = getObject("frmItemEdit");
		if(frm) {
			var objSel = frm.elements["selView"];
			var index = objSel.selectedIndex;
			if( index < 0 ) {
				alert("������ �׸��� ������ �ּ���");
			} else {
				var delSeq = objSel.value;
				
				//mPoll.deleteItem(delSeq);
				objSel.options[index] = null;
			}
		}
	}
</script>