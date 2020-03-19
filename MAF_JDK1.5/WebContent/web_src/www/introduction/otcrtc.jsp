<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<Script>
    function ShowInfo(org_code) {
        var t = "";
        if(org_code == '1') {
            t = "HMEWA";        // West Europe
        } else if (org_code == '2') {
            t = "HMEP";
        } else if (org_code == '3') {
            t = "H05AA";    //MIAMI OFFICE
        } else if (org_code == '4') {
            t = "H07AA";  //M. East & Africa
        } else if (org_code == '5') {
            t = "H01AA";    // ASIA REGIONAL OFFICE
        } else if (org_code == '6') {
            t = "YGAF";    /// CHEONAN TRANING CENTER
        } else if (org_code == '7') {
            t = "";    /// HAOS
        } else if (org_code == '8') {
            t = "";    /// CHINA
        } else if (org_code == '9') {
            t = "";    /// HMJ Cheonan
        } else if (org_code == '10') {
            t = "";    /// HMA
        } else if (org_code == '11') {
            t = "";    /// HAC
        } else if (org_code == '12') {
            t = "";    /// HMI
        } else if (org_code == '13') {
            t = "";    /// S/Africa
        } else if (org_code == '14') {
            t = "";    /// Australia
        } else if (org_code == '15') {
            t = "";    /// CHILE
        }
        if (t != "") {
            document.location = CPATH + "/info/OrgInfo.do?cmd=view&org_cd="+t;
        }
    }
</script>
<br>
<table width="100%">
    <tr>
        <td align='center'>
<script>
    write_flash("/hkmca/images/hmc/otcrtc_hyundai.swf",667,381);
</script></td>
</tr>
</table>