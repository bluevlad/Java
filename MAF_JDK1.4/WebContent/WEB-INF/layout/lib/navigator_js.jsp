<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page language="java"%>
<script src="${CPATH}/js/lib.paging.js"></script>
<script>
	// ����¡ ���̺귯�� ����ϱ�
	PG = new Paging(300);	// �� 300����

	//��������
	//PG.config = {
	//	thisPageStyle: 'font-weight: bold; color:#33B7FB',
	//	//otherPageStyle: 'color: #000000',
	//	itemPerPage: 15,	// ����Ʈ ��ϼ�
	//	pagePerView: 15		// �������� �׺���̼� �׸��
	//};

	// �ѵΰ� ������ �ٲܶ��� �̷��Ե�..
//	PG.config.prevIcon = '/image/icon/prev.gif';
//	PG.config.nextIcon = '/image/icon/next.gif';

	document.write(PG);
</script>
