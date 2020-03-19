<%@ page contentType = "text/html; charset=utf-8" %>

<%
response.setHeader("Content-Type","application/vnd.ms-excel");
String filename =(String) request.getAttribute("filename");
if (filename == null) filename="excel";
response.setHeader("Content-Disposition", "attachment;filename="+filename+".xml");
response.setHeader("Content-Description", "postech course support system");
%>
<?xml version="1.0" encoding="UTF-8"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Author>Author</Author>
  <LastAuthor>LastAuthor</LastAuthor>
  <Created>${now()}T02:28:00Z</Created>
  <LastSaved>${now()}T02:28:00Z</LastSaved>
  <Company>miraenet</Company>
  <Version>1</Version>
 </DocumentProperties>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>11025</WindowHeight>
  <WindowWidth>17745</WindowWidth>
  <WindowTopX>240</WindowTopX>
  <WindowTopY>105</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
 <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Center"/>
   <Borders/>
   <Font ss:FontName="돋움" x:CharSet="129" x:Family="Modern" ss:Size="11"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="s17" ss:Name="쉼표 [0]">
   <NumberFormat ss:Format="_-* #,##0_-;\-* #,##0_-;_-* &quot;-&quot;_-;_-@_-"/>
  </Style>
  <Style ss:ID="s23">
   <NumberFormat ss:Format="Short Date"/>
  </Style>
  <Style ss:ID="s24">
   <NumberFormat ss:Format="[ENG][$-F800]dddd\,\ mmmm\ dd\,\ yyyy"/>
  </Style>
  <Style ss:ID="s26">
   <NumberFormat ss:Format="yyyy&quot;/&quot;m&quot;/&quot;d;@"/>
  </Style>
  <Style ss:ID="s27" ss:Parent="s17">
   <NumberFormat ss:Format="_-* #,##0.0_-;\-* #,##0.0_-;_-* &quot;-&quot;_-;_-@_-"/>
  </Style>
  <Style ss:ID="s28" ss:Parent="s17">
   <NumberFormat ss:Format="_-* #,##0.00_-;\-* #,##0.00_-;_-* &quot;-&quot;_-;_-@_-"/>
  </Style>
  <Style ss:ID="s30" ss:Parent="s17">
   <NumberFormat ss:Format="_-* #,##0.0000_-;\-* #,##0.0000_-;_-* &quot;-&quot;_-;_-@_-"/>
  </Style>
  <Style ss:ID="s32">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Font ss:FontName="돋움" x:CharSet="129" x:Family="Modern" ss:Size="11"
    ss:Bold="1"/>
  </Style>
  <Style ss:ID="s33">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Font ss:FontName="돋움" x:CharSet="129" x:Family="Modern" ss:Size="11"/>
  </Style>
  <Style ss:ID="table_header">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <Font ss:FontName="돋움" x:CharSet="129" x:Family="Modern" ss:Size="11" ss:Bold="1"/>
   <Interior ss:Color="#ffff99" ss:Pattern="Solid"/>
  </Style>
  <Style ss:ID="table_data">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <Font ss:FontName="돋움" x:CharSet="129" x:Family="Modern" ss:Size="11" />
  </Style>  
 </Styles>
<jsp:include page="${JMF_INFO.file}" flush="true"/>
</Workbook>