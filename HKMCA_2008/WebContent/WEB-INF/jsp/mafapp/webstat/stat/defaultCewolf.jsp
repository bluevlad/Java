<%@ page contentType="text/html; charset=euc-kr"%>
<%@taglib uri='/WEB-INF/tld/cewolf.tld' prefix='cewolf' %>

<script language="javascript" src="${CPATH}/js/fusionChart.js"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	<tr> 
		<td>
		<cewolf:chart  id="verticalBar"  title="2006년 9월 일자별 접속자 현황 "   
				type="verticalBar"  xaxislabel="date"  yaxislabel="user"
				showlegend="false" >
    		<cewolf:data>
        		<cewolf:producer id="categoryData" usecache="true"/>
    		</cewolf:data>

		</cewolf:chart>

		<cewolf:img   chartid="verticalBar"  renderer="/cewolf"  width="680"  height="300" removeAfterRender="true"/>
		</td>
	</tr>
</table>
<graph bgcolor='e1f5ff' caption='Decline in Net Interest Margins of Asian Banks (1995-2001)' subCaption='(in Percentage %)' yaxismaxvalue="2" yaxisminvalue='-2.5' yaxisname='Points' xaxisname='Country' hovercapbg='FFFFDD' hovercapborder='000000' numdivlines='4' numberSuffix='%25'> 
   <set name='Taiwan' value='-0.33' color='F6BD0F'/> 
   <set name='Malaysia' value='-0.27' color='FF6600'/> 
   <set name='Hong Kong' value='0.40' color='8BBA00' alpha='70'/> 
   <set name='Philippines' value='0.6' color='F984A1'/> 
   <set name='Singapore' value='-0.8' color='A66EDD' alpha='70'/>
   <set name='Thailand' value='-2.2' color='B2FF66'/> 
   <set name='India' value='1.2' color='AFD8F8'/> 
<trendlines>
   <line startvalue='0.8' displayValue='Good' color='FF0000' thickness='1' isTrendZone='0'/>
   <line startvalue='-0.4' displayValue='Bad' color='009999' thickness='1' isTrendZone='0'/>
</trendlines>
</graph>
<script type="text/javascript">
	chart = new fusionChart('3DColumn',680,480);
	chart.setXmlDataUrl('${controlaction}?cmd=data');
	document.write(chart);
</script>