<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/js/commons.jspf"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>汇总查询详细信息</title>

<link href="${pageContext.request.contextPath }/css/examine1.css"
	rel="stylesheet">
<link href="css/kuang.css?version=1" rel="stylesheet" type="text/css" />
<link href="css/alert-handle.css?version=1" rel="stylesheet"
	type="text/css" />
<link href="css/alert-handle2.css?version=4" rel="stylesheet"
	type="text/css" />

<style class="cp-pen-styles">
.warning {
	font-size: 1em;
	text-align: center;
	background-color: #6DA807;
	border: 1px solid #6DA807;
	border-radius: 10px;
	box-shadow: 1vw 3vh 10vh rgba(109, 168, 7, 0.8);
	background-size: 3em 3em;
	background-image: linear-gradient(-45deg, transparent 0em, transparent 0.8em, #96D923
		0.9em, #96D923 2.1em, transparent 2.1em, transparent 2.9em, #96D923
		3.1em);
	-webkit-animation: warning-animation 750ms infinite linear;
	-moz-animation: warning-animation 750ms infinite linear;
	animation: warning-animation 750ms infinite linear;
}

@-webkit-keyframes warning-animation {
  0% {
    background-position: 0 0;
  }
  100% {
    background-position: 3em 0;
  }
}
@-moz-keyframes warning-animation {
  0% {
    background-position: 0 0;
  }
  100% {
    background-position: 3em 0;
  }
}
@keyframes warning-animation {
  0% {
    background-position: 0 0;
  }
  100% {
    background-position: 3em 0;
  }
}
.progress {
	height: 25;
}

.progress .warning {
	height: 100;
}
</style>
<style type="text/css">
a {
	text-align: center;
}
</style>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/tipswindown.js?version=1"></script>
<script type="text/javascript" src="js/document.js?version=6"></script>
<script type="text/javascript">
	$("document").ready(function() {
		$("#progress").hide();

	});
	var AccreditationId;
	function hideProgress(accreditationId) {
		AccreditationId = accreditationId;
		$("#documentHref").hide();
		$("#progress").show();
		$.ajax({
			type : "POST",
			dataType : "text",
			url : "aboutTaskAction_compressionDocument",
			data : {
				accreditationId : accreditationId

			},
			success : callbackDownload
		});

	}

	function callbackDownload(data) {
		window.location.href = "aboutTaskAction_downLoadDocuments?accreditationId="
				+ AccreditationId;
		$("#documentHref").show();
		$("#progress").hide();

	}
</script>

<script type="text/javascript">
	var currentFlag;
	$(document).ready(function() {
		$("#idCard").click(function() {
			accreditationAuditDocumentUI("${id}", "IdCard");
			currentFlag = "idCardFlag";
		});
	});
	$(document).ready(function() {
		$("#enforceCard").click(function() {
			accreditationAuditDocumentUI("${id}", "EnforceCard");
			currentFlag = "enforceCardFlag";
		});
	});
	$(document).ready(function() {
		$("#orderChangeNotice").click(function() {
			accreditationAuditDocumentUI("${id}", "OrderChangeNotice");
			currentFlag = "orderChangeNoticeFlag";
		});
	});
	$(document).ready(function() {
		$("#recordInquest").click(function() {
			accreditationAuditDocumentUI("${id}", "RecordInquest");
			currentFlag = "recordInquestFlag";
		});
	});
	$(document).ready(function() {
		$("#sitePhotos").click(function() {
			accreditationAuditDocumentUI("${id}", "SitePhotos");
			currentFlag = "sitePhotosFlag";
		});
	});
	$(document).ready(function() {
		$("#recordInv").click(function() {
			accreditationAuditDocumentUI("${id}", "RecordInv");
			currentFlag = "recordInvFlag";
		});
	});
	$(document).ready(function() {
		$("#recordPaper").click(function() {
			accreditationAuditDocumentUI("${id}", "RecordPaper");
			currentFlag = "recordPaperFlag";
		});
	});
	$(document).ready(function() {
		$("#businessLicense").click(function() {
			accreditationAuditDocumentUI("${id}", "BusinessLicense");
			currentFlag = "businessLicenseFlag";
		});
	});
	//-----------------------------------------------------------------------------分隔线--------------------------------------------------------------------------------------------
	$(document).ready(function() {
		$("#finalReport").click(function() {

			pTableAuditDocumentUI("${accreditation.pTable.id}", "FinalReport");
			currentFlag = "finalReportFlag";
		});
	});
	$(document).ready(
			function() {
				$("#proofServicePT").click(
						function() {
							pTableAuditDocumentUI("${accreditation.pTable.id}",
									"ProofServicePT");
							currentFlag = "proofServicePTFlag";
						});
			});
	$(document).ready(function() {
		$("#recordSheet").click(function() {
			pTableAuditDocumentUI("${accreditation.pTable.id}", "RecordSheet");
			currentFlag = "recordSheetFlag";
		});
	});
	//-----------------------------------------------------------------------------分隔线--------------------------------------------------------------------------------------------

	$(document).ready(
			function() {
				$("#fineNote").click(
						function() {

							pClosingReportAuditDocumentUI(
									"${accreditation.pClosingReport.id}",
									"FineNote");
							currentFlag = "fineNoteFlag";
						});
			});
	$(document).ready(
			function() {
				$("#proofServicePC").click(
						function() {
							pClosingReportAuditDocumentUI(
									"${accreditation.pClosingReport.id}",
									"ProofServicePC");
							currentFlag = "proofServicePCFlag";
						});
			});

	function accreditationAuditDocumentUI(accreditationId, documentName) {

		$.ajax({
			type : "POST",
			dataType : "html",
			url : "accreditationAction_auditDocumentUI",
			data : {
				accreditationId : accreditationId,
				documentName : documentName,
			},
			success : callback
		});

	}
	function pTableAuditDocumentUI(pTableId, documentName) {

		$.ajax({
			type : "POST",
			dataType : "html",
			url : "pTableAction_auditDocumentUI",
			data : {
				pTableId : pTableId,
				documentName : documentName,
			},
			success : callback
		});

	}
	function pClosingReportAuditDocumentUI(pClosingReportId, documentName) {

		$.ajax({
			type : "POST",
			dataType : "html",
			url : "pClosingReportAction_auditDocumentUI",
			data : {
				pClosingReportId : pClosingReportId,
				documentName : documentName,
			},
			success : callback
		});

	}
</script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Collect the nav links, forms, and other content for toggling -->
			<ul class="nav navbar-nav">

				<li class="active"><a href="javascript:void(0);">案件详细信息</a></li>


			</ul>
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div class="container">
		<div class="table-responsive col-md-8 col-md-offset-0">
			<table class="table table-bordered text-center"
				style="font-size: 1.2em">
				<caption style="font-size: 1.9em">
					郑州市中原区城市管理行政执法局<br>案件基本信息
				</caption>
				<tr>
					<th width="10%" rowspan="6">当事人</th>
					<th width="10%" rowspan="3">单位</th>
					<th>名称</th>
					<td colspan="5"><core:if
							test="${accreditation.unitName != '' }">${accreditation.unitName }</core:if>
						<core:if test="${accreditation.unitName == '' }">/</core:if></td>
				</tr>
				<tr>
					<th width="15%">法定代表人<br>（负责人）
					</th>
					<td><core:if test="${accreditation.leRepresentative != '' }">${accreditation.leRepresentative }</core:if>
						<core:if test="${accreditation.leRepresentative == '' }">/</core:if>
					</td>
					<th width="10%">电话</th>
					<td colspan="3"><core:if
							test="${accreditation.unitTel != '' }">${accreditation.unitTel }</core:if>
						<core:if test="${accreditation.unitTel == '' }">/</core:if></td>
				</tr>
				<tr>
					<th>地址</th>
					<td colspan="5"><core:if
							test="${accreditation.unitAddress != '' }">${accreditation.unitAddress }</core:if>
						<core:if test="${accreditation.unitAddress == '' }">/</core:if></td>
				</tr>
				<tr>
					<th rowspan="3">个人</th>
					<th>姓名</th>
					<td width="20%"><core:if
							test="${accreditation.personnelName != '' }">${accreditation.personnelName }</core:if>
						<core:if test="${accreditation.personnelName == '' }">/</core:if>
					</td>
					<th>性别</th>
					<td width="10%"><core:if
							test="${accreditation.personnelName != '' }">
							<core:if test="${accreditation.sex != '' }">${accreditation.sex }</core:if>
							<core:if test="${accreditation.sex == '' }">/</core:if>
						</core:if> <core:if test="${accreditation.personnelName == '' }">/</core:if>
					</td>
					<th width="10%">年龄</th>
					<td width="20%"><core:if
							test="${accreditation.userAge != null && userAge != 0}">${accreditation.userAge }岁</core:if>
						<core:if test="${accreditation.userAge == null }">/</core:if></td>
				</tr>
				<tr>
					<th>住址</th>
					<td colspan="5"><core:if
							test="${accreditation.userAddress != null }">${accreditation.userAddress }</core:if>
						<core:if test="${accreditation.userAddress == null }">/</core:if>
					</td>
				</tr>
				<tr>
					<th>身份证号码</th>
					<td colspan="2"><core:if
							test="${accreditation.idNumber != null }">${accreditation.idNumber }</core:if>
						<core:if test="${accreditation.idNumber == null }">/</core:if></td>
					<th>电话</th>
					<td colspan="2"><core:if
							test="${accreditation.userTel != null }">${accreditation.userTel }</core:if>
						<core:if test="${accreditation.userTel == null }">/</core:if></td>
				</tr>
				<tr>
					<th colspan="2">案件来源</th>
					<td colspan="6">${accreditation.caseSource.name }</td>
				</tr>
				<tr>
					<th colspan="2">立案时间</th>
					<td colspan="6"><fmt:formatDate
							value="${accreditation.bigCaptainDate }" type="date" /></td>
				</tr>
				<tr>
					<th colspan="2">基本案情</th>
					<td colspan="6" style="min-height: 150; text-align: left;"><span
						style="word-wrap: break-word; word-break: break-all;">${accreditation.baseCase }</span></td>
				</tr>
				<core:if test="${accreditation.pClosingReport != null }">
					<tr>
						<th colspan="2">基本案件及执行情况</th>

						<td colspan="6" style="min-height: 200; text-align: left;">
							<span style="word-wrap: break-word; word-break: break-all;">${accreditation.pClosingReport.basicCase }</span>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;当事人于
								<fmt:formatDate
									value="${accreditation.pClosingReport.paymentDate }"
									type="date" />
								缴纳了罚款，现已执行到位。
							</p>
						</td>
					</tr>
				</core:if>
				<tr>
					<th colspan="2">承办人</th>
					<td colspan="6"><span>主办人：${accreditation.sponsor.name }</span>
						<span>协办人：${accreditation.assistantHandle.name }</span><br /></td>
				</tr>
			</table>
		</div>
		<div class="col-sm-3 col-sm-offset-1 " style="margin-top: 15%;">
			<nav>
				<ul class="nav nav-pills nav-stacked affix bg-hex"
					style="padding: 10px;">
					<li class="active"><core:if
							test="${!empty accreditation.pClosingReport.indCommDate }">
							<a href="javascript:void(0);">已结案</a>
						</core:if> <core:if
							test="${empty accreditation.pClosingReport.indCommDate }">
							<a
								href="aboutTaskAction_viewCurrentImageOrderAccId?accreditationId=${accreditation.id }"
								target="_blank">查看案件进展图</a>
						</core:if></li>
					<br />
					<li class="active"><a href="javascript:void(0);">案件相关审批表</a></li>
					<li><a
						href="accreditationAction_queryPDF?accreditationId=${accreditation.id }"
						target="_blank">立案审批表</a></li>
					<core:if test="${accreditation.pNotice != null }">
						<core:if test="${accreditation.pNotice.legalFlag }">

							<li><a
								href="pNoticeAction_downloadPNoticeDocument?pdfDocumentName=${accreditation.pNotice.pdfDocument.name }&reallyName=${accreditation.pNotice.pdfDocument.reallyName}">事先告知书（下载）</a></li>

						</core:if>
					</core:if>
					<core:if test="${accreditation.pTable != null }">
						<li><a
							href="pTableAction_queryPDF?pTableId=${accreditation.pTable.id }"
							target="_blank">处理审批表</a></li>
					</core:if>
					<core:if test="${accreditation.pDecide != null }">
						<core:if test="${accreditation.pDecide.legalFlag }">
							<li><a
								href="pDecideAction_downloadPNoticeDocument?pdfDocumentName=${accreditation.pDecide.pdfDocument.name }&reallyName=${accreditation.pDecide.pdfDocument.reallyName}">行政处罚决定书（下载）</a></li>
						</core:if>
					</core:if>
					<core:if test="${accreditation.pClosingReport != null }">
						<li><a
							href="pClosingReportAction_queryPDF?pClosingReportId=${accreditation.pClosingReport.id }"
							target="_blank">结案报告</a></li>
					</core:if>

					<core:if test="${accreditation.pTable != null }">
						<core:if test="${accreditation.pTable.caseLevel == '重大' }">
							<li><a
								href="pTableAction_queryPDF3?pTableId=${accreditation.pTable.id }"
								target="_blank">业委会审批决定书</a></li>
						</core:if>
						<core:if test="${accreditation.pTable.caseLevel == '特大' }">
							<li><a
								href="pTableAction_queryPDF2?pTableId=${accreditation.pTable.id }"
								target="_blank">案审委审批决定书</a></li>
						</core:if>
					</core:if>
					<li><a href="javascript:void(0)" id="documentHref"
						onclick="hideProgress('${accreditation.id }')">文书下载</a>
						<div class="progress" id="progress">
							<div class="warning">请稍等,正在处理</div>
						</div></li>
					<li class="active"><a href="javascript:history.go(-1)">返回上一步</a></li>
				</ul>
			</nav>
		</div>
	</div>
	<center></center>
	<div class="qqserver">

		<div class="qqserver_fold">
			<div></div>
		</div>

		<div class="qqserver-body" style="display: block;">
			<div class="qqserver-header">
				<div></div>
				<span class="qqserver_arrow"></span>
				<div class="title"></div>
			</div>
			<ul>


				<label style="font-size: 14px">&nbsp;&nbsp;立案审批表文书</label>
				<li><img id="idCardImg"
					<core:if test="${accreditation.idCardFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.idCardFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">当事人身份证</div> <core:if
						test="${!empty accreditation.idCard }">
						<div class="anniu">
							<a id="idCard" href="javascript:void(0)">查看</a>
						</div>
					</core:if></li>


				<li><img id="enforceCardImg"
					<core:if test="${accreditation.enforceCardFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.enforceCardFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">执法证</div> <core:if
						test="${!empty accreditation.enforceCard }">
						<div class="anniu">
							<a id="enforceCard" href="javascript:void(0);">查看</a>
						</div>
					</core:if></li>
				<li><img id="orderChangeNoticeImg"
					<core:if test="${accreditation.orderChangeNoticeFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.orderChangeNoticeFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">责令改正通知书</div> <core:if
						test="${!empty accreditation.orderChangeNotice }">
						<div class="anniu">
							<a id="orderChangeNotice" href="javascript:void(0)">查看</a>
						</div>
					</core:if></li>

				<li><img id="recordInquestImg"
					<core:if test="${accreditation.recordInquestFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.recordInquestFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">现场勘验笔录</div> <core:if
						test="${!empty accreditation.recordInquest }">
						<div class="anniu">
							<a id="recordInquest" href="javascript:void(0)">查看</a>
						</div>
					</core:if></li>
				<li><img id="sitePhotosImg"
					<core:if test="${accreditation.sitePhotosFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.sitePhotosFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">现场照片</div> <core:if
						test="${!empty accreditation.sitePhotos }">
						<div class="anniu">
							<a id="sitePhotos" href="javascript:void(0)">查看</a>
						</div>
					</core:if></li>
				<li><img id="recordInvImg"
					<core:if test="${accreditation.recordInvFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.recordInvFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">调查询问笔录</div> <core:if
						test="${!empty accreditation.recordInv }">
						<div class="anniu">
							<a id="recordInv" href="javascript:void(0)">查看</a>
						</div>
					</core:if></li>
				<li><img id="recordPaperImg"
					<core:if  test="${accreditation.recordPaperFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.recordPaperFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">笔录纸</div> <core:if
						test="${!empty accreditation.recordPaper }">
						<div class="anniu">
							<a id="recordPaper" href="javascript:void(0)">查看</a>
						</div>
					</core:if></li>
				<core:if test="${!empty accreditation.pTable }">
					<div style="height: 10"></div>
					<label style="font-size: 14px">&nbsp;&nbsp;处罚审批表文书</label>
				</core:if>

				<core:if test="${!empty accreditation.unitName }">
					<li><img id="businessLicenseImg"
						<core:if  test="${accreditation.businessLicenseFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
						<core:if test="${!accreditation.businessLicenseFlag }">src="images/alert/cuohao.png"</core:if> />

						<div class="wenzi">营业执照</div> <core:if
							test="${!empty accreditation.businessLicenseFlag }">
							<div class="anniu">
								<a id="businessLicense" href="javascript:void(0)">查看</a>
							</div>
						</core:if></li>
				</core:if>
				<!---------------------------------------------------------------------------------- 分隔线-------------------------------------------------------------------------------- -->

				<li><img id="proofServicePTImg"
					<core:if test="${accreditation.pTable.proofServicePTFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.pTable.proofServicePTFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">送达回证</div> <core:if
						test="${!empty accreditation.pTable.proofServicePT }">
						<div class="anniu">
							<a id="proofServicePT" href="javascript:void(0);">查看</a>
						</div>
					</core:if></li>
				<li><img id="finalReportImg"
					<core:if test="${accreditation.pTable.finalReportFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.pTable.finalReportFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">案件调查终结报告</div> <core:if
						test="${!empty accreditation.pTable.finalReport }">
						<div class="anniu">
							<a id="finalReport" href="javascript:void(0)">查看</a>
						</div>
					</core:if></li>

				<li><img id="recordSheetImg"
					<core:if test="${accreditation.pTable.recordSheetFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.pTable.recordSheetFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">调查终结报告笔录纸</div> <core:if
						test="${!empty accreditation.pTable.recordSheet }">
						<div class="anniu">
							<a id="recordSheet" href="javascript:void(0)">查看</a>
						</div>
					</core:if></li>


				<!---------------------------------------------------------------------------------- 分隔线-------------------------------------------------------------------------------- -->
				<core:if test="${!empty accreditation.pClosingReport }">

					<div style="height: 10"></div>
					<label style="font-size: 14px">&nbsp;&nbsp;结案报告文书</label>
				</core:if>
				<li><img id="proofServicePCImg"
					<core:if test="${accreditation.pClosingReport.proofServicePCFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.pClosingReport.proofServicePCFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">送达回证</div> <core:if
						test="${!empty accreditation.pClosingReport.proofServicePC }">
						<div class="anniu">
							<a id="proofServicePC" href="javascript:void(0);">查看</a>
						</div>
					</core:if></li>
				<li><img id="fineNoteImg"
					<core:if test="${accreditation.pClosingReport.fineNoteFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
					<core:if test="${!accreditation.pClosingReport.fineNoteFlag }">src="images/alert/cuohao.png"</core:if> />

					<div class="wenzi">案件调查终结报告</div> <core:if
						test="${!empty accreditation.pClosingReport.fineNote }">
						<div class="anniu">
							<a id="fineNote" href="javascript:void(0)">查看</a>
						</div>
					</core:if></li>

			</ul>
		</div>

	</div>


	<div id="ceng" style="display: none;">
		<div id="china" class="simScrollCont">
			<div class="mainlist">
				<ul style="list-style: none;" id="documentUL">
				</ul>
			</div>
			<div class="btnbox">

				<input type="button" value="关闭" onclick="confirmTerm(2);"
					class="confirmBtn">
			</div>
		</div>

	</div>
</body>
</html>