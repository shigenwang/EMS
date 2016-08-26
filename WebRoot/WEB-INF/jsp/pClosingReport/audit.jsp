<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/js/commons.jspf"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>行政处罚结案报告</title>

<link href="css/examine1.css" rel="stylesheet">
<link href="css/kuang.css?version=5" rel="stylesheet" type="text/css" />
<link href="css/alert-handle.css" rel="stylesheet" type="text/css" />
<link href="css/alert-handle2.css?version=1" rel="stylesheet"
	type="text/css" />


<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/tipswindown.js?version=1"></script>
<script type="text/javascript" src="js/document.js?version=1"></script>
<script type="text/javascript">
	function validate(outCome) {
		var suggest = $.trim($("#suggest").attr("value"));

		if (suggest == "" && outCome == "驳回") {
			$("#errorInf").html("驳回时必须输入意见");
			return false;

		} else if (suggest != "" && outCome == "批准") {
			$("#errorInf").html("批准时不能输入意见");
			return false;
		}

		if (outCome == "批准") {
			var proofServicePCFlag = $.trim($("#proofServicePCFlag").attr(
					"value"));
			var fineNoteFlag = $.trim($("#fineNoteFlag").attr("value"));

			if (proofServicePCFlag == "" || proofServicePCFlag == "false"
					|| fineNoteFlag == "" || fineNoteFlag == "false"

			) {
				$("#errorInf").html("批准时必须通过全部上传的文件");
				return false;
			}

		}
	}

	var currentFlag;

	$(document).ready(function() {
		$("#fineNote").click(function() {

			auditDocumentUI("${id}", "FineNote");
			currentFlag = "fineNoteFlag";
		});
	});
	$(document).ready(function() {
		$("#proofServicePC").click(function() {
			auditDocumentUI("${id}", "ProofServicePC");
			currentFlag = "proofServicePCFlag";
		});
	});

	//得到单个文书
	function auditDocumentUI(pClosingReportId, documentName) {

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

</head>

<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">


			<ul class="nav navbar-nav">
				<core:forEach items="${taskTypeList }" var="taskType">
					<core:if test="${taskType=='accreditationSign' }">
						<li <core:if test="${type=='立案审批表' }">class="active"</core:if>><a
							href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=立案审批表">立案审批表任务</a></li>
					</core:if>
					<core:if test="${taskType=='pNoticeSign' }">
						<li <core:if test="${type=='事先告知书' }">class="active"</core:if>><a
							href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=事先告知书">处罚告知书任务</a></li>
					</core:if>
					<core:if test="${taskType=='pTableSign' }">
						<li <core:if test="${type=='处罚审批表' }">class="active"</core:if>><a
							href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚审批表">处罚审批表任务</a></li>
					</core:if>
					<core:if test="${taskType=='pDecideSign' }">
						<li <core:if test="${type=='处罚决定书' }">class="active"</core:if>><a
							href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚决定书">处罚决定书任务</a></li>
					</core:if>
					<core:if test="${taskType=='pClosingReportSign' }">
						<li <core:if test="${type=='处罚结案报告' }">class="active"</core:if>><a
							href="${pageContext.request.contextPath }/aboutTaskAction_findTaskListById?type=处罚结案报告">处罚结案报告任务</a></li>
					</core:if>
				</core:forEach>
			</ul>
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="container">
		<div class="table-responsive col-md-10 col-md-offset-1">
			<table class="table table-bordered text-center"
				style="font-size: 16px">
				<caption>
					郑州市中原区城市管理行政执法局<br>行&nbsp;政&nbsp;处&nbsp;罚&nbsp;结&nbsp;案&nbsp;报&nbsp;告
				</caption>

				<tr>
					<th width="20%">基本案情及执行情况</th>
					<td style="text-align: left;"><span style="line-height: 1.8em">${basicCase }</span>

						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当事人
							<s:date name="paymentDate" format="yyyy月MM日dd日" />
							缴纳了罚款，现已执行到位。
						</p></td>

				</tr>

				<tr>
					<th colspan="1">承办人意见</th>
					<td colspan="6" height="60">
						<!--<div>案件主办人： 徐闯</div>
                <div>协办人：张艺</div>
                <div>2015.4.9</div>--> <core:if
							test="${hostFlag&&joinFlag}">
							<p>建议结案</p>
						</core:if> <core:if test="${hostFlag}">
							<span>${accreditation.sponsor.name }</span>
						</core:if> <core:if test="${joinFlag}">
							<span>${accreditation.assistantHandle.name }</span>
						</core:if> <core:if test="${hostFlag&&joinFlag}">
							<div>
								<s:date name="hostDate" format="yyyy.MM.dd" />
							</div>
						</core:if> <!--  <div>2015.4.9</div> -->


					</td>
				</tr>
				<tr>
					<th colspan="1">承办部门意见</th>
					<td colspan="6"><core:if test="${captainFlag}">
							<p>同意</p>
							<div>
								<s:date name="captainDate" format="yyyy.MM.dd" />
							</div>
							<div>${captainName }</div>
						</core:if> <core:if test="${!captainFlag}">
							<p>&nbsp;&nbsp;&nbsp;</p>
							<div>&nbsp;&nbsp;&nbsp;</div>
							<div>&nbsp;&nbsp;&nbsp;</div>
						</core:if></td>
				</tr>
				<tr>
					<th colspan="1">法制机构审核意见</th>
					<td colspan="6"><core:if test="${legalFlag}">
							<p>同意</p>
							<div>
								<s:date name="legalDate" format="yyyy.MM.dd" />
							</div>
							<div>${ legalName}</div>
						</core:if> <core:if test="${!legalFlag}">
							<p>&nbsp;&nbsp;&nbsp;</p>
							<div>&nbsp;&nbsp;&nbsp;</div>
							<div>&nbsp;&nbsp;&nbsp;</div>
						</core:if></td>
				</tr>
				<tr>
					<th colspan="1">领导审批意见</th>
					<td colspan="6"><core:if test="${chiefFlag}">
							<p>同意</p>
							<div>
								<s:date name="bigCaptainDate" format="yyyy.MM.dd" />
							</div>
							<div>${bigCaptainName}</div>
						</core:if> <core:if test="${!chiefFlag}">
							<p>&nbsp;&nbsp;&nbsp;</p>
							<div>&nbsp;&nbsp;&nbsp;</div>
							<div>&nbsp;&nbsp;&nbsp;</div>
						</core:if></td>
				</tr>

				<tr>
					<th width="20%">备注</th>
					<td>&nbsp;</td>
				</tr>
			</table>

		</div>
		<form class="form-horizontal col-md-10 col-md-offset-1"
			action=pClosingReportAction_submitTask>
			<core:if test="${!empty captainSuggest }">
				<core:if test="${currentSign=='中队长审批' }">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<p class="bg-primary">上次我的审批意见</p>
							<textarea class="form-control" rows="5" style="margin-top: 0"
								readonly="readonly">${captainSuggest }</textarea>
						</div>
					</div>
				</core:if>
				<core:if test="${currentSign!='中队长审批' }">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<p class="bg-primary">上次中队长审批意见</p>
							<textarea class="form-control" rows="5" style="margin-top: 0"
								readonly="readonly">${captainSuggest }</textarea>
						</div>
					</div>
				</core:if>

			</core:if>
			<core:if test="${!empty legSuggest }">
				<core:if test="${currentSign=='法制科审批' }">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<p class="bg-primary">上次我的审批意见</p>
							<textarea class="form-control" rows="5" style="margin-top: 0"
								readonly="readonly">${legSuggest }</textarea>
						</div>
					</div>
				</core:if>
				<core:if test="${currentSign!='法制科审批' }">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<p class="bg-primary">上次法制科审批意见</p>
							<textarea class="form-control" rows="5" style="margin-top: 0"
								readonly="readonly">${legSuggest }</textarea>
						</div>
					</div>
				</core:if>

			</core:if>
			<core:if test="${!empty bigCaptainSuggest }">
				<core:if test="${currentSign=='大队长审批' }">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<p class="bg-primary">上次我的审批意见</p>
							<textarea class="form-control" rows="5" style="margin-top: 0"
								readonly="readonly">${bigCaptainSuggest }</textarea>
						</div>
					</div>
				</core:if>
				<core:if test="${currentSign!='大队长审批' }">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<p class="bg-primary">上次大队长审批意见</p>
							<textarea class="form-control" rows="5" style="margin-top: 0"
								readonly="readonly">${bigCaptainSuggest }</textarea>
						</div>
					</div>
				</core:if>
			</core:if>

			<core:if test="${!empty ICSuggest }">
				<core:if test="${currentSign=='部门主要领导审批' }">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<p class="bg-primary">上次我的审批意见</p>
							<textarea class="form-control" rows="5" style="margin-top: 0"
								readonly="readonly">${ICSuggest }</textarea>
						</div>
					</div>
				</core:if>
				<core:if test="${currentSign!='部门主要领导审批' }">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<p class="bg-primary">上次业委会审批意见</p>
							<textarea class="form-control" rows="5" style="margin-top: 0"
								readonly="readonly">${ICSuggest }</textarea>
						</div>
					</div>
				</core:if>
			</core:if>
			<div class="row">

				<core:if test="${currentSign!='主办审批'&&currentSign!='协办审批' }">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<p class="bg-primary">本次我的意见</p>

						<textarea class="form-control" rows="5" id="suggest"
							<core:if test="${currentSign=='法制科指导' }">name="legGuideSuggest"</core:if>
							<core:if test="${currentSign=='中队长审批' }">name="captainSuggest"</core:if>
							<core:if test="${currentSign=='法制科审批' }">name="legSuggest"</core:if>
							<core:if test="${currentSign=='大队长审批' }">name="bigCaptainSuggest"</core:if>
							<core:if test="${currentSign=='部门主要领导审批' }">name="ICSuggest"</core:if>></textarea>
					</div>
				</core:if>

				<div class="col-md-12" style="height: 50px;"></div>

				<input name="taskId" type="hidden" value="${taskId }"> <input
					name="pClosingReportId" type="hidden" value="${id }"> <input
					type="hidden" name="currentSign" value="${currentSign }" />


				<div style="margin-top: 22" class="col-md-7 ">
					<label id="errorInf" style="font-size: 16px; color: red"
						for="firstname"
						class="col-xs-4 col-sm-10 col-md-10 col-lg-7  control-label"></label>
				</div>


				<s:if test="#outcomeList!=null && #outcomeList.size()>0">

					<core:forEach items="${outcomeList}" var="outCome"
						varStatus="statu">
						<core:if test="${statu.count==1 }">
							<div class="col-md-1 col-md-offset-1">
								<br> <input type="submit" name="outCome"
									class="btn btn-primary" value="${outCome }"
									onclick="return validate(this.value)">

							</div>
						</core:if>
						<core:if test="${statu.count==2 }">
							<div class="col-md-1 col-md-offset-2">
								<br> <input type="submit" name="outCome"
									class="btn btn-primary" value="${outCome }"
									onclick="return validate(this.value)">

							</div>
						</core:if>
					</core:forEach>



				</s:if>



				<div class="qqserver">
					<div class="qqserver_fold">
						<div></div>
					</div>
					<div class="qqserver-body" style="display: block;">
						<div class="qqserver-header">
							<div></div>
							<span class="qqserver_arrow"></span>
							<div class="title">结案报告文书</div>
						</div>
						<ul>




							<li><img id="proofServicePCImg"
								<core:if test="${proofServicePCFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
								<core:if test="${!proofServicePCFlag }">src="images/alert/cuohao.png"</core:if> />

								<div class="wenzi">送达回证</div> <core:if
									test="${!empty proofServicePC }">
									<div class="anniu">
										<a id="proofServicePC" href="javascript:void(0);">查看</a>
									</div>
								</core:if></li>
							<li><img id="fineNoteImg"
								<core:if test="${fineNoteFlag }">src="images/alert/20150729042348932_easyicon_net_32.png"</core:if>
								<core:if test="${!fineNoteFlag }">src="images/alert/cuohao.png"</core:if> />

								<div class="wenzi">案件调查终结报告</div> <core:if
									test="${!empty fineNote }">
									<div class="anniu">
										<a id="fineNote" href="javascript:void(0)">查看</a>
									</div>
								</core:if></li>



						</ul>

					</div>

				</div>
				<input type="hidden" id="proofServicePCFlag"
					name="proofServicePCFlag" value="${proofServicePCFlag }" /> <input
					type="hidden" id="fineNoteFlag" name="fineNoteFlag"
					value="${fineNoteFlag }" />
		</form>

	</div>
	<div id="ceng" style="display: none;">
		<div id="china" class="simScrollCont">
			<div class="mainlist">
				<ul style="list-style: none;" id="documentUL">
				</ul>
			</div>
			<div class="btnbox">
				<input type="button" value="通过" onclick="confirmTerm(1);"
					class="confirmBtn"> <input type="button" value="不通过"
					onclick="confirmTerm(0);" class="confirmBtn"> <input
					type="button" value="关闭" onclick="confirmTerm(2);"
					class="confirmBtn">
			</div>
		</div>

	</div>
</body>
</html>