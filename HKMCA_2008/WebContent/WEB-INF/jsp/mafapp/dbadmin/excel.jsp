<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<Worksheet ss:Name="TableIndex">
  <Table ss:ExpandedColumnCount="20" ss:ExpandedRowCount="1" x:FullColumns="10"
   x:FullRows="1" ss:DefaultColumnWidth="60" ss:DefaultRowHeight="13.5">
	<Row>
		<Cell ss:StyleID="table_header"><Data ss:Type="String">Table Name</Data></Cell>
		<Cell ss:StyleID="table_header"><Data ss:Type="String">Comment</Data></Cell>
	</Row>
	<c:forEach items="${tables}" var="item">
	  <Row>
	    <Cell ss:StyleID="table_data"><Data ss:Type="String"><c:out value="${fn:toLowerCase(item.name)}"/></Data></Cell>
	    <Cell ss:StyleID="table_data"><Data ss:Type="String"><c:out value="${item.comments}"/></Data></Cell>
		</Row>
	</c:forEach>
	</Table>
	<WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>	
</Worksheet>

<c:forEach items="${tableInfo}" var="columns">
<Worksheet ss:Name="${columns.key}">
  <Table ss:ExpandedColumnCount="20" ss:ExpandedRowCount="1" x:FullColumns="10"
   x:FullRows="1" ss:DefaultColumnWidth="60" ss:DefaultRowHeight="13.5">
	<Row>
		<Cell ss:MergeAcross="3"><Data ss:Type="String">Table Name : ${columns.key} </Data></Cell>
	</Row>
  <Row>
    <Cell ss:StyleID="table_header"><Data ss:Type="String">id</Data></Cell>
    <Cell ss:StyleID="table_header"><Data ss:Type="String">Column Name</Data></Cell>
    <Cell ss:StyleID="table_header"><Data ss:Type="String">Pk</Data></Cell>
    <Cell ss:StyleID="table_header"><Data ss:Type="String">Null</Data></Cell>
    <Cell ss:StyleID="table_header"><Data ss:Type="String">Data Type</Data></Cell>
    <Cell ss:StyleID="table_header"><Data ss:Type="String">Length</Data></Cell>
    <Cell ss:StyleID="table_header"><Data ss:Type="String">Default Value</Data></Cell>
    <Cell ss:StyleID="table_header"><Data ss:Type="String">Comments</Data></Cell>
  </Row>
	<c:forEach items="${columns.value}" var="item">
	  <Row>
	    <Cell ss:StyleID="table_data"><Data ss:Type="String"><c:out value="${item.id}"/></Data></Cell>
	    <Cell ss:StyleID="table_data"><Data ss:Type="String"><c:out value="${fn:toLowerCase(item.name)}"/></Data></Cell>
	    <Cell ss:StyleID="table_data"><Data ss:Type="String"><c:out value="${item.pk}"/></Data></Cell>
	    <Cell ss:StyleID="table_data"><Data ss:Type="String"><c:out value="${item.nullable}"/></Data></Cell>
	    <Cell ss:StyleID="table_data"><Data ss:Type="String"><c:out value="${item.type}"/></Data></Cell>
	    <Cell ss:StyleID="table_data"><Data ss:Type="String"><c:out value="${item.dlength}"/></Data></Cell>
	    <Cell ss:StyleID="table_data"><Data ss:Type="String"><c:out value="${item.data_default}"/></Data></Cell>
	    <Cell ss:StyleID="table_data"><Data ss:Type="String"><c:out value="${item.comments}"/></Data></Cell>		
	  </Row>
	</c:forEach>
	  </Table>
	<WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
</Worksheet>
</c:forEach>
