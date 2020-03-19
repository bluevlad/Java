/*
 * Message.java, @ 2005-05-12
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package miraenet;

/**
 * @author goindole
 *
 */
public class Message {
    public final static String INVALID_REQUEST = "�߸��� ��û�Դϴ�.";
    /**
     * �������� ���� �޼��� ����
     */
    // ��� ����
    public final static String INSERT_OK = " ���������� �����͸� ��� �Ͽ����ϴ�.";
    // ���� ����
    public final static String DELETE_OK = " ���������� �����͸� ���� �Ͽ����ϴ�.";
    // ���� ����
    public final static String UPDATE_OK = " ���������� �����͸� �����Ͽ����ϴ�.";
    // ���� ����
    public final static String JOIN_OK = "�� ȸ�������� ���� �帳�ϴ�.";
    // ���Ƹ� ��� ���� 
    public final static String CLUB_CLOSE_OK = " ���������� ���Ǿ����ϴ�.";
    // ����4���̻��� ��� ���,���� �Ұ���
    public final static String INSERT_DIARY = "�ش��Ͽ� ������ 4���̻� ����� �� �����ϴ�.";
    // �̹���õ
    public final static String RECM_NO = " �̹� ��õ�Ͽ����ϴ�.";
    // ��õ ����
    public final static String RECM_OK = " ���������� ��õ�Ͽ����ϴ�.";
    // �̹� ���Ƹ����Ե�
    public final static String JOIN_NO = "�� �̹� ���Ե� ���͵��Դϴ�.";
    // �¶����� ��� ����
    public final static String POLL_OK = "���������� �¶��μ�����  ��� �Ͽ����ϴ�. ����� ������ �����ϰڽ��ϴ�.";

    /**
     * ���� ���� �޼��� ����
     */
    // ��� ����
    public final static String INSERT_FAIL = "�� �����͸� ��ϵ��� ������ �߻��Ͽ����ϴ�.";
    // ���� ����
    public final static String DELETE_FAIL = "�� �����͸� �������� ������ �߻��Ͽ����ϴ�.";
    // ���� ����
    public final static String UPDATE_FAIL = "�� �����͸� �������� ������ �߻��Ͽ����ϴ�.";
    //  token fail
    public final static String INVALID_TOKEN = "�� �߸��� ������ ��û�Դϴ�. �������� ��η� ���� �Ͻðų� ��������  [���ΰ�ħ] �� �ٽ� �õ��� �ּ���.";    

    /**
     * ������ ���� ��� �޼��� ����
    **/
    // ��ٱ��� ��ǰ �߰�
    public final static String INSERT_BASKET = "�� ��ٱ��Ͽ� ��ǰ�� �߰��߽��ϴ�.";
    // ��ٱ��� ��ǰ ����
    public final static String UPDATE_BASKET = "�� ��ٱ��Ͽ��� ��ǰ������ �����߽��ϴ�.";
    // ��ٱ��� ��ǰ ����
    public final static String DELETE_BASKET = "�� ��ٱ��Ͽ��� ��ǰ�� �����߽��ϴ�.";

    // ��ٱ��� ��ǰ �߰� ����
    public final static String INSERT_BASKET_FAIL = "�� ��ٱ��Ͽ� ��ǰ�� �߰����� ���߽��ϴ�.";
    // ��ٱ��� ��ǰ ���� ����
    public final static String UPDATE_BASKET_FAIL = "�� ��ٱ��Ͽ��� ��ǰ������ �������� ���߽��ϴ�.";
    // ��ٱ��� ��ǰ ���� ����
    public final static String DELETE_BASKET_FAIL = "�� ��ٱ��Ͽ��� ��ǰ�� �������� ���߽��ϴ�.";
    // ��ٱ��Ͽ� ������ �ΰ��̻� �������� �Ҷ�
    public final static String INSERT_BASKET_SJT_FAIL = "�� ���������� ��ٱ��Ͽ� �Ѱ��� ���� �� �ֽ��ϴ�.";


    // �ֹ��� ���������� �Ϸ�������
    public final static String INSERT_ORDER_OK = "�� ���������� �ֹ��� �Ϸ�Ǿ����ϴ�..";
    // �ֹ��� ���������ܿ�
    public final static String INSERT_ORDER_FAIL = "�� �ֹ��� ������ �߻��߽��ϴ�.";
    // ����Ȯ���� ���������� ó���������
    public final static String PAY_ORDER_OK = "�� ���������� �����Ϸᰡ ó���Ǿ����ϴ�..";
    // ����Ȯ���� ���������ܿ�
    public final static String PAY_ORDER_FAIL = "�� �����Ϸ� ó���� ������ �߻��߽��ϴ�.";
    // ������ ������� ���
    public final static String PAY_CANCEL = "�� ������ ����ϼ̽��ϴ�.";
    // �ֹ��� ������� ���
    public final static String ORDER_CANCEL = "�� �ֹ��� ����ϼ̽��ϴ�.";

    //------------------------------------------------------------------------------------------------------------------------------

    // �л���� �������
    public final static String ERROR_HAKGI  = "�� �л���� �Է� ���� �ʾҽ��ϴ�. <br> �л�� �Է� �� ����� �����մϴ�.";
    public final static String PERMIT_OK = "�� ������ �������� �����Ͽ����ϴ�.";
    public final static String CANCEL_OK = "�� ������ �������� ���� �ź��Ͽ����ϴ�.";
    public final static String BA3_DUP = "�� �̹� ��ϵǾ� �ִ� ����Ÿ�Դϴ�. <br> Ȯ�� �� �ٽ� �õ� �ϼ���.";

    /**
     * �������� ��� �޼���
     */
    // �ڵ尡 �ߺ��ɶ�
    public final static String ID_DUPLICATE = "�� �̹� ��ϵǾ� �ִ� �ڵ��Դϴ�.";
    // �ڵ尡 �ߺ����� ������
    public final static String ID_DUPLICATEOK = "�� ��� ������ �ڵ� �Դϴ�.";
    // ��ȸ�� ������ ������Ʈ�� ����Ÿ�� ã�� �� ������
    public final static String DATA_NOT_FOUND = "�� ��û�Ͻ� �����͸� ã�� �� �����ϴ�.";
    // �̹� ��ϵ� �ֹε�� ��ȣ�� ������
    public final static String SSNB_DUPLICATE = "�� �̹� ��ϵǾ� �ִ� �ֹε�Ϲ�ȣ �Դϴ�.";
    // �̹� ��ϵ� ȸ�� ��ȣ�� ������
	public final static String MEMNO_DUPLICATE = "�� �̹� ��ϵǾ� �ִ� ȸ����ȣ�Դϴ�.";

    /**
     * ������ �޼��� ����
     */
    // DB ���� ����
    public final static String CONNECTION_REFUS = "�� ���� ����ڰ� �����ϴ�. ����Ŀ� �ٽ� �õ� �Ͻñ� �ٶ��ϴ�.";
    // ���ϴٿ�ε� ����
    public final static String FILE_NOT_FOUND = "�� ��û�Ͻ� ������ ã�� �� �����ϴ�.";
    //��й�ȣ ���� �ʾ�����
    public final static String PWD_NOT_MATCH = "�� ��й�ȣ�� ���� �ʽ��ϴ�.";

    /**
     * ��� �޼��� ����
     */
    // ��� ������ ������ �����Ͽ� ���� �Ұ�
    public final static String EDU_DEL_NOT_ALLOW = "�� ������ ��û�� ����� ������ �����Ǿ� �ֽ��ϴ�.";
    // ��� �ڵ� ������ ������ �����Ͽ� ���� �Ұ�
    public final static String EDU_EDT_NOT_ALLOW = "�� �̹� ������ �����Ǿ� �־� ����ڵ带 ������ �� �����ϴ�.";
    // ���� ������ ������ �����Ͽ� ���� �Ұ�
    public final static String GRP_DEL_NOT_ALLOW = "�� ������ ��û�� ������ ������ �����Ǿ� �ֽ��ϴ�.";
    // ���� ������ ������ �����Ͽ� ���� �Ұ�
    public final static String GRP_EDT_NOT_ALLOW = "�� ������ ��û�� ������ ������ �����Ǿ� �ֽ��ϴ�.";
    // ���� ������ �л������� �����Ͽ� ���� �Ұ�
    public final static String SJT_DEL_NOT_ALLOW = "�� ������ ��û�� ���� �л������� �����Ǿ� �ֽ��ϴ�.";
    // ���� ������ �л������� �����Ͽ� ���� �Ұ�
    public final static String SJT_EDT_NOT_ALLOW = "�� ������ ��û�� ���� �л������� �����Ǿ� �ֽ��ϴ�.";

    /**
    * �¶��� ��
    */
    // �¶����򰡸� �л��鿡�� �����Ҷ� �������� �л��� ���� �� ����
    public final static String STD_NOT_FOUND = "�� �������� �л��� �����ϴ�.";
    // �¶������� ������ ������ �����Ͽ� ������ �л��鿡�� �����Ҷ� ������ ������ ���� �� ����
    public final static String EXM_NOT_FOUND = "�� ������ ���蹮���� �����ϴ�.";
    // �¶��� �򰡸� �л��鿡�� �����Ͽ��� ��
    public final static String EXM_SUBMIT_SUCCESS = "�� ���蹮���� �л��鿡�� ���� �Ͽ����ϴ�.";
    // �¶������� ������ ������ �����Ͽ� ������ �л��鿡�� �����Ҷ� �л��鿡�� �̹� ������ �����Ͽ��� �� ����
    public final static String EXM_SUBMIT_NOT_SUCCESS = "�� ���蹮���� �л��鿡�� �̹� ���� �Ͽ����ϴ�.";
    // �¶����� ���������� ������ ������ ������ ��
    public final static String EXM_BNK_DELETE = "�� �����ߴ� ������ �����Ͽ����ϴ�.";
    // �¶����� ���������� ������ ������ ������ �� ����
    public final static String EXM_BNK_DELETE_FAIL = "�� �����Ϸ��� ������ �����ϴ�.";
    // �¶����� ���������� ������ ������ ����� ��
    public final static String EXM_BNK_INSERT = "�� ������ ������ ��� �Ͽ����ϴ�.";
    // �¶����� ���������� ������ ������ ����� �� ����
    public final static String EXM_BNK_INSERT_FAIL = "�� ������ ������ ��ϵ��� �ʾҽ��ϴ�.";
    // �¶����� ���������� ������ ������ ������ ��
    public final static String EXM_BNK_UPDATE = "�� ������ ������ �����Ǿ����ϴ�.";
    // �¶����� ���������� ������ ������ ������ �� ����
    public final static String EXM_BNK_UPDATE_FAIL = "�� ������ ������ �������� �ʾҽ��ϴ�.";
    // �¶����� �������࿡�� ������ ������ �����Ͽ� ������ ��
    public final static String EXM_TMP_INSERT = "�� ������ ����Ͽ����ϴ�.";
    // �¶����� �������࿡�� ������ ������ �����Ͽ� ������ �� ����
    public final static String EXM_TMP_INSERT_FAIL = "�� ������ ������ �̹� �־� ������ ������ �� �����ϴ�.";
    // �¶����� �������࿡�� ������ �����ߴ� ������ ������ ��
    public final static String EXM_TMP_DELETE = "�� �����ߴ� ������ ����Ͽ����ϴ�.";
    // �¶����� �������࿡�� ������ �����ߴ� ������ ������ �� ����
    public final static String EXM_TMP_DELETE_FAIL = "�� �����ߴ� ������ ������� ���߽��ϴ�.";
    // �¶����� �������࿡�� ������ �����ߴ� ������ ������ ��
    public final static String EXM_TMP_UPDATE = "�� �����ߴ� ������ �����Ͽ����ϴ�.";
    // �¶����� �������࿡�� ������ �����ߴ� ������ ������ �� ����
    public final static String EXM_TMP_UPDATE_FAIL = "�� �����ߴ� ������ �������� ���߽��ϴ�.";
    // �¶����� ���
    public final static String EXM_OMG_INSERT = "�� �¶����򰡸� ��� �Ͽ����ϴ�.";
    // �¶����� ��� ����
    public final static String EXM_OMG_INSERT_FAIL = "�� �¶����򰡰� �̹� ��ϵǾ� �ֽ��ϴ�.";
    // �¶����� ����
    public final static String EXM_OMG_UPDATE = "�� �¶����򰡸� �����Ͽ����ϴ�.";
    // �¶����� ���� ����
    public final static String EXM_OMG_UPDATE_FAIL = "�� �¶����򰡸� �������� ���߽��ϴ�.";
    // �¶����� ����
    public final static String EXM_OMG_DELETE = "�� �¶����򰡸� �����Ͽ����ϴ�.";
    // �¶����� ���� ����
    public final static String EXM_OMG_DELETE_FAIL = "�� �¶����򰡸� �������� ���߽��ϴ�.";
    // �¶����򰡸� ������ �������� ������ ��
    public final static String EXM_RND_REG = "�� �л��鿡�� ������ �����Ǿ����ϴ�.";
    // �¶����򰡸� ������ �������� ������ �� ����
    public final static String EXM_RND_REG_FAIL = "�� �ڵ��� ������ ���Ͽ�  ���蹮���� ������ �� �����ϴ�.";
    // �л��鿡�� ������ ���� ����
    public final static String EXM_STD_DELETE = "�� �л��鿡�� ������ ������ �����Ͽ����ϴ�.";
    // �л��鿡�� ������ ���� ���� ����
    public final static String EXM_STD_DELETE_FAIL = "�� �л��鿡�� ������ ������ ���ų� �ڵ������ ���Ͽ� �л��鿡�� ������ ������ �������� ���߽��ϴ�.";
    // �л��� ������ Ǯ���� �� �޽���
    public final static String EXM_STD_SUBMIT = "�� Ǯ���� ������ ��� �Ͽ����ϴ�.";
    // �л��� ������ Ǯ���� �� ���� �޽���
    public final static String EXM_STD_SUBMIT_FAIL = "�� Ǯ���� ������ ������� ���߽��ϴ�.";
    // ������� ���
    public final static String EXM_STD_RESUBMIT = "�� ������� ��� �Ͽ����ϴ�.";
    // ������� ��� ���� �޽���
    public final static String EXM_STD_RESUBMIT_FAIL = "�� �̹� �¶����򰡿� ���ø� �ϼ̽��ϴ�.";
    // �������� ����
    public final static String EXM_STD_RESULT_DELETE = "�� �����ߴ� ������ ������ ���� �Ͽ����ϴ�.";
    // �������� ���� ���� �޽���
    public final static String EXM_STD_RESULT_DELETE_FAIL = "�� �����ߴ� ������ ������ �������� ���߽��ϴ�.";

    /**
    * ���� (calendar)
    */
    // ���� ��Ͻ� �� ������ ����.
    public final static String CAL_EXIST = "�� �̹� ������ ��ϵǾ� �����Ƿ�, �����Ͽ� �߰��Ͻñ� �ٶ��ϴ�.";


    /**
    * �¶��� ����
    */
    // �¶��� ���� ���
    public final static String SMG_MNG_INSERT = "�� �¶��� ������ ��� �Ͽ����ϴ�..";
    // �¶��� ���� ��� ����
    public final static String SMG_MNG_INSERT_FAIL = "�� �̹� ��ϵ� �¶��� ������ �־� ��Ͽ� �����Ͽ����ϴ�.";
    // �¶��� ���� ���� �޽���
    public final static String SMG_MNG_DELETE = "�� �ش� ������ ���� �Ͽ����ϴ�.";
    // �¶��� ���� ���� ���� �޽���
    public final static String SMG_MNG_DELETE_FAIL = "�� �ش� ������ �������� ���߽��ϴ�.";
    // �¶��� ���� ���� �޽���
    public final static String SMG_MNG_UPDATE = "�� �ش� ������ ���� �Ͽ����ϴ�.";
    // �¶��� ���� ���� ���� �޽���
    public final static String SMG_MNG_UPDATE_FAIL = "�� �ش� ������ �������� ���߽��ϴ�.";
    // �¶��� ���� ���� ��� �޽���
    public final static String SMG_QUE_INSERT = "�� ������ ��� �Ͽ����ϴ�.";
    // �¶��� ���� ���� ��� ���� �޽���
    public final static String SMG_QUE_INSERT_FAIL = "�� ������ ������� ���߽��ϴ�.";
    // �¶��� ���� ���� ���� �޽���
    public final static String SMG_QUE_DELETE = "�� �ش� ������ ���� �Ͽ����ϴ�.";
    // �¶��� ���� ���� ���� ���� �޽���
    public final static String SMG_QUE_DELETE_FAIL = "�� �ش� ������ ���� �Ͽ����ϴ�.";
    // �¶��� ���� ���� ���� �޽���
    public final static String SMG_QUE_UPDATE = "�� �ش� ������ ���� �Ͽ����ϴ�.";
    // �¶��� ���� ���� ���� �����޽���
    public final static String SMG_QUE_UPDATE_FAIL = "�� �ش� ������ �������� ���߽��ϴ�.";
    // �л��� �¶��� ������ ����
    public final static String SMG_QUE_STD_INSERT = "�� �ۼ��� ������ ��� �Ͽ����ϴ�.";
    // �л��� �¶��� ������ ���� ���� �޽���
    public final static String SMG_QUE_STD_INSERT_FAIL = "�� �ۼ��� ������ ��� ���� ���߽��ϴ�.";
    // �̹� ������ ���� ���� ���
    public final static String SMG_QUE_STD_ALREADY = "�� �̹� ������ �ۼ� �Ͽ����ϴ�.";


    /**
     * ���̳��� ����
     */
    // ���̳��� ���� ����
    public final static String SEM_DEL_FAIL =  "�� ���̳��� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���̳��� ���� ���� ����
    public final static String SEM_MEM_DEL_FAIL =  "�� ���̳��� ���� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���̳��� ���� ����
    public final static String SEM_EDT_FAIL =  "�� ���̳��� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���̳��� ���� ���� ����
    public final static String SEM_MEM_EDT_FAIL =  "�� ���̳��� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���̳��� ��� ����
    public final static String SEM_INS_FAIL =  "�� ���̳��� ��� �� ������ �߻��Ͽ����ϴ�.";
    // ���̳��� ���� ��� ����
    public final static String SEM_MEM_INS_FAIL =  "�� ���̳��� ���� ��� �� ������ �߻��Ͽ����ϴ�.";

    /**
     * ���� ��� ����
     */
    // ���� �Ϲ����� ��� ����
    public final static String QIZ_INSERT_OK =  "�� ���� �Ϲ������� ��ϵǾ����ϴ�.";
    // ���� �Ϲ����� ��� ����
    public final static String QIZ_INSERT_FAIL =  "�� ���� �Ϲ����� ��� �� ������ �߻��Ͽ����ϴ�.";
    // ���� �Ϲ����� ���� ����
    public final static String QIZ_UPDATE_OK =  "�� ���� �Ϲ������� �����Ǿ����ϴ�.";
    // ���� �Ϲ����� ���� ����
    public final static String QIZ_UPDATE_FAIL =  "�� ���� �Ϲ����� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���� �Ϲ����� ���� ����
    public final static String QIZ_DELETE_OK =  "�� ��� �����Ǿ����ϴ�.";
    // ���� �Ϲ����� ���� ����
    public final static String QIZ_DELETE_FAIL =  "�� ���� ���� �� ������ �߻��Ͽ����ϴ�.";

    // ���� ���� ��� ����
    public final static String QIZ_EXP_INSERT_OK =  "�� ���� ������ ��ϵǾ����ϴ�.";
    // ���� ���� ��� ����
    public final static String QIZ_EXP_INSERT_FAIL =  "�� ���� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���� ���� ���� ����
    public final static String QIZ_EXP_UPDATE_OK =  "�� ���� ������ �����Ǿ����ϴ�.";
    // ���� ���� ���� ����
    public final static String QIZ_EXP_UPDATE_FAIL =  "�� ���� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���� ���� ���� ����
    public final static String QIZ_EXP_DELETE_OK =  "�� ���� ������ �����Ǿ����ϴ�.";
    // ���� ���� ���� ����
    public final static String QIZ_EXP_DELETE_FAIL =  "�� ���� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���� ���� ���� ����
    public final static String QIZ_FILE_DELETE_OK =  "�� ���� ������ �����Ǿ����ϴ�.";
    // ���� ���� ���� ����
    public final static String QIZ_FILE_DELETE_FAIL =  "�� ���� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���� �������� ��� ����
    public final static String QIZ_ANS_INSERT_OK  =  "�� ���� ���������� ����Ͽ����ϴ�.";
    // ���� �������� ��� ����
    public final static String QIZ_ANS_INSERT_FAIL  =  "�� ���� �������� ��� �� ��    ���� �߻��Ͽ����ϴ�.";
    // ���� �������� ���� ����
    public final static String QIZ_ANS_UPDATE_FAIL  =  "�� ���� �������� ��� �� ������ �߻��Ͽ����ϴ�.";
    // ���� �н� ����
    public final static String QIZ_PASS_OK = "�� ���� ������ ����ġ���� ����Ͽ� \n ���� ������ �Ѿ�� �� �ֽ��ϴ�.";
    // ���� �н� ����
    public final static String QIZ_PASS_FAIL = "�� ���� ������ ����ġ ���� �̴޵Ǵ� �����Դϴ�.\n �ٽ� �ѹ� ������ Ǯ���ֽñ� �ٶ��ϴ�.";


    /*
     * ���л� ������û
     */
    // ���л� ������û ����.
    public final static String REQ_STD_CREATE =  "�� ������ ���ǿ� ���� ������û �Ǿ����ϴ�.";
    public final static String REQ_STD_CREATE_FAIL =  "�� ������ ���ǿ� ���� ������û�� ������ �߻��Ͽ����ϴ�.";
    public final static String REQ_STD_CREATE_USED =  "�� ���� ���������Դϴ�..";
    public final static String REQ_STD_NULL =  "<br><bR>�� ����� ������ �����ϴ�.<br><br><br>���Ǹ� �����Ͻ��� �ʾҰų�, �̹� ��û�� �����Դϴ�.";

    //������û ��Ұ���..
    public final static String REQCANCEL_OK =  "�� ������û ��� ��û�� �����Ͽ����ϴ�.";
    public final static String REQCANCEL_REQ =  "�� ������û ��� ��û�� ����Ͽ����ϴ�.";

    /**
     * �ǽð� ����
     */
    // ID, �����ڵ尡 �Ѿ���� ���� ���
    public final static String MAL_PAM_NO = "�� �ǽð� ���� ���� ��� ������ �����ϴ�.";
    // ���� ���۰������� session�� ����� ���
    public final static String MAL_SESSION_CLOSED =  "�� ���� ���� �� ������ ����Ǿ����ϴ�.";
    // ���� ���۰��� ����
    public final static String MAL_SEND_FAIL =  "�� ���� ���� �� ������ �߻��Ͽ����ϴ�.";


    /**
     * ���Ƹ� ��ȭ�� ����
     */
    // ���Ƹ� ��ȭ�� ���� ����
    public final static String CHT_DEL_FAIL =  "�� ���Ƹ� ��ȭ�� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���Ƹ� ��ȭ�� ���� ���� ����
    public final static String CHT_MEM_DEL_FAIL =  "�� ���Ƹ� ��ȭ�� ���� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���Ƹ� ��ȭ�� ���� ����
    public final static String CHT_EDT_FAIL =  "�� ���Ƹ� ��ȭ�� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���Ƹ� ��ȭ�� ���� ���� ����
    public final static String CHT_MEM_EDT_FAIL =  "�� ���Ƹ� ��ȭ�� ���� ���� �� ������ �߻��Ͽ����ϴ�.";
    // ���Ƹ� ��ȭ�� ��� ����
    public final static String CHT_INS_FAIL =  "�� ���Ƹ� ��ȭ�� ��� �� ������ �߻��Ͽ����ϴ�.";
    // ���Ƹ� ��ȭ�� ���� ��� ����
    public final static String CHT_MEM_INS_FAIL =  "�� ���Ƹ� ��ȭ�� ���� ��� �� ������ �߻��Ͽ����ϴ�.";

    /**
     *
     */
    // ��������
    public final static String GRD_APPROVAL      =  "�� �������������� ���� �Ͽ����ϴ�.";
    public final static String GRD_DEPT_APPROVAL =  "�� �ش� �а��� ���������ڸ� �����ڷ� ���� �Ͽ����ϴ�.";
    public final static String GRD_RESERVATION   =  "�� ������ �����Ͽ����ϴ�.";

    /**
     *
     */
    // �������
    public final static String DGR_APPEND   =  "�� ������ ����Ͽ����ϴ�.";

    /**
     * ��������
     */
    public final static String CHG_OK   =  "�� ���������� �����Ͽ����ϴ�.";
    public final static String CHG_NO   =  "�� ���������� �ź��Ͽ����ϴ�.";
    public final static String CHG_HAKGI1   =  "�� �бⰡ ���������ϴ�.";
    public final static String CHG_HAKGI2   =  "�� �бⰡ ���������ϴ�.";
    public final static String CHG_ERR2   =  "�� �ڽ��� ���� ������û�� �� �� �����ϴ�.";
    public final static String CHG_ERR3   =  "�� �������� �ʴ� �й��Դϴ�.";
    public final static String CHG_ERR4   =  "�� ������ 3ȸ �̻� ó���� �� �����ϴ�.";
    public final static String CHG_ELSE   =  "�� �������� ��û ����� ������ �߻��Ͽ����ϴ�.";
    public static final String DUPLICATION = "�ߺ��Է�(�����Ͱ� �����մϴ�.)";
     /**
     * ����,��ϰ���
     */
    public final static String ACCOUNT_OK = " ������ �Ա� ��û�� �Ϸ� �Ǿ����ϴ�. <br><br> ��� �Ⱓ ���� �ش� ���࿡ �Ա� �ϼ���.";
    public final static String WHAN_GBN = " �̹� ȯ�� ó���� �Ϸ� �Ǿ����ϴ�.";
    public final static String WHAN_AGREE_OK = " ���������� ȯ�� ���� �Ǿ����ϴ�.";
    public final static String WHAN_CANCEL_OK = " ���������� ȯ�� ��� �Ǿ����ϴ�.";
    public final static String DENG_YN = " ��ϱ� ���� ������ �����ϴ�. <br> ȯ�� ��û�� �Ұ��� �մϴ�.";
    public final static String STATE_OK = " ���� ���°� ���л��� �ƴմϴ�. <br><br> ���� �̿��� �Ұ��� �մϴ�.";
    public final static String JANG_GIGAN_ERR = " ���� ��û �Ⱓ�� �ƴմϴ�.";
    public final static String DENG_GIGAN_ERR = " ��ϱ� ���� �Ⱓ�� �ƴմϴ�.";
    public final static String WHAN_GIGAN_ERR = " ȯ�� �Ⱓ�� �ƴմϴ�.";
    public final static String DELETE_NO = " ���� ������� �ڵ� �Դϴ�. ������ �Ұ��� �մϴ�.";
    public final static String FILE_ERR = " ������ ���� ���� �ʽ��ϴ�.";
    public final static String ENTER_CD = " �ð��� ��ϻ��� <br> ���� �̿��� �Ұ����մϴ�.";
    public final static String REG_FLAG = " �̹� ����� �Ϸ� �Ǿ����ϴ�.";
    public final static String JANG_YN = " ���л����� ���� �Ϸ� �Ǿ����ϴ�. <br> ���н�û�� �Ұ��� �մϴ�..";
    public final static String REG_GBN = " ��ϱ� ���� ������ �����ϴ�. <br> ���� ��û�� �Ұ��� �մϴ�.";

    /**
     * �ֹ�����
     */
    public final static String ORDER_OK   =  " �ֹ��� ���������� �̷�������ϴ�.";
    public final static String ORDER_FAIL =  " �ֹ��� ������ �߻��߽��ϴ�.";
    public final static String PAY_OK   =  " ���������� ���� �Ϸ� �Ǿ����ϴ�.";
    public final static String PAY_FAIL =  " ������ �����Ͽ����ϴ�.";

    public final static String Sisul =  " 2004�� 3�� 1�Ϻ��� ���� �˴ϴ�.";

    /**
     * ��������
     */
    public final static String CBT_LEND_OK   =  " CBT�� ������ �Ǿ����ϴ�.";
    public final static String CBT_LEND_FAIL   =  " CBT�� ������ �����߽��ϴ�. �ٽ� �������ֽʽÿ�.";
    public final static String CBT_LEND_CANCEL   =  " CBT�� ������ ��ҵǾ����ϴ�.";
    public final static String CBT_LEND_CANCEL_FAIL   =  " CBT�� ������ ��Ұ� �����߽��ϴ�. �ٽ� ������ֽʽÿ�";
    public final static String LAB_LEND_OK   =  " �ڽ��� ������ �Ǿ����ϴ�.";
    public final static String LAB_LEND_FAIL   =  " �ڽ��� ������ �����߽��ϴ�. �ٽ� �������ֽʽÿ�.";
    public final static String LAB_LEND_CANCEL   =  " �ڽ��� ������ ��ҵǾ����ϴ�.";
    public final static String LAB_LEND_CANCEL_FAIL   =  " �ڽ��� ������ ��Ұ� �����߽��ϴ�. �ٽ� ������ֽʽÿ�";
    public final static String VID_LEND_OK   =  " �ڽ��� ������ �Ǿ����ϴ�.";
    public final static String VID_LEND_FAIL   =  " �ڽ��� ������ �����߽��ϴ�. �ٽ� �������ֽʽÿ�.";
    public final static String VID_LEND_CANCEL   =  " �ڽ��� ������ ��ҵǾ����ϴ�.";
    public final static String VID_LEND_CANCEL_FAIL   =  " �ڽ��� ������ ��Ұ� �����߽��ϴ�. �ٽ� ������ֽʽÿ�";
    public final static String LOCKER_LEND_OK   =  " �繰�� ������ �Ǿ����ϴ�.";
    public final static String LOCKER_LEND_FAIL   =  " �繰�� ������ �����߽��ϴ�. �ٽ� �������ֽʽÿ�.";
    public final static String LOCKER_LEND_CANCEL   =  " �繰�� ������ ��ҵǾ����ϴ�.";
    public final static String LOCKER_LEND_CANCEL_FAIL   =  " �繰�� ������ ��Ұ� �����߽��ϴ�. �ٽ� ������ֽʽÿ�";
    public final static String STUDY_GROUP_OK   =  " ���͵�׷� ��û�� �����Ǿ����ϴ�.";
    public final static String STUDY_GROUP_FAIL   =  " ���͵�׷� ��û�� �����߽��ϴ�. �ٽ� ��û���ֽʽÿ�.";
    public final static String CONSULT_CARD_OK   =  " ���ī�� �ۼ��� �Ϸ�Ǿ����ϴ�.";
    public final static String CONSULT_CARD_FAIL   =  " ���ī�� �ۼ��� �����߽��ϴ�. �ٽ� �ۼ����ֽʽÿ�.";
    public final static String VISIT_CONSULT_OK   =  " �湮��� ������ �Ϸ�Ǿ����ϴ�.";
    public final static String VISIT_CONSULT_FAIL   =  " �湮��� ������ �����߽��ϴ�. ���������ڸ�  �����Ͽ� �ٽ� �ۼ����ֽʽÿ�.";
    public final static String EVALUATION_OK   =  " Evaluation ��û�� �Ϸ�Ǿ����ϴ�.";
    public final static String EVALUATION_FAIL   =  " Evaluation ��û�� �����߽��ϴ�. �ٽ� �ۼ����ֽʽÿ�.";
    public final static String FILING_OK   =  " Filing ��û�� �Ϸ�Ǿ����ϴ�.";
    public final static String FILING_FAIL   =  " Filing ��û�� �����߽��ϴ�. �ٽ� �ۼ����ֽʽÿ�.";
    public final static String RECORD_OK   =  " �����߼� ��û�� �Ϸ�Ǿ����ϴ�.";
    public final static String RECORD_FAIL   =  " �����߼� ��û�� �����߽��ϴ�. �ٽ� �ۼ����ֽʽÿ�.";
    public final static String FAIR_OK   =  " ��������ȸ�� ��û�Ǿ����ϴ�.";
    public final static String FAIR_FAIL   =  " ��������ȸ ��û�� �����߽��ϴ�. �ٽ� ��û���ֽʽÿ�.";
}
