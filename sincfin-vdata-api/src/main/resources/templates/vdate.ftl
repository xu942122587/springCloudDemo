<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta name="format-detection" content="telephone=no, email=no">
<meta name="robots" content="none" />
<meta name="apple-mobile-web-app-title" content="万家贷">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=1"/>
<style type="text/css" media="screen">
* { margin: 0; padding: 0; outline: 0; border: 0; box-sizing: border-box; -moz-box-sizing: border-box; -webkit-box-sizing: border-box; }
.clearfix:before, .clearfix:after { content: ''; display: table; line-height: 0; }
.clearfix:after { clear: both; }
.clearfix { *zoom: 1}
._pull-left { float: left; }
._pull-right { float: right; }
._t-l { text-align: left; }
._t-r { text-align: right; }
._t-c { text-align: center; }
/*._wrap { width: 1200px; margin: 0 auto; padding: 20px 0 0; }
._table { margin-bottom: 20px; }
._table ._title { text-align: center; line-height: 34px; font-size: 16px; background-color: #348c5b; border: 1px solid #333333; border-bottom: 0; }
._table p._p1 { font-size: 20px; line-height: 40px; }
._table p._p2 { font-size: 18px; line-height: 40px; }
._table ._list { line-height: 34px; font-size: 14px; color: #323232; border-top: 1px solid #333333; border-left: 1px solid #333333; }

._table1 ._list dl { width: 14.28%; text-align: center; }

._table ._list dt, ._table ._list dd { border-bottom: 1px solid #333333; border-right: 1px solid #333333; font-weight: bold; }

._table ._list dt { background-color: #a2bfde; }

._red { color: #ff0000 !important; }

._green { color: #26a000 !important; }

._bc1 { background-color: #dbdbdb !important; }

._bc2 { background-color: #E3DFED !important; }

._bc3 { background-color: #F9BF8F !important; }

table { border-collapse: collapse; width: 100%; text-align: center; }

._wrap { width: 1200px; margin: 0 auto; padding: 20px 0 0; }

._table { margin-bottom: 20px; }

._table p._p1 { font-size: 20px; line-height: 40px; }

._table p._p2 { font-size: 18px; line-height: 40px; }

._table table { line-height: 34px; border: 1px solid #333333; font-size: 14px; color: #323232; }

._table table caption { font-size: 16px; border-left: 1px solid #333333; border-right: 1px solid #333333; border-top: 1px solid #333333; background-color: #fff1d4; }

._table table tr th, ._table table tr td { font-weight: bold; border-right: 1px solid #333333; border-bottom: 1px solid #333333; }

._table table thead { background-color: #abd188; }

._table { margin-bottom: 20px; }

._table ._title { text-align: center; line-height: 34px; font-size: 16px; background-color: #fff1d4; border: 1px solid #333333; border-bottom: 0; }

._table p._p1 { font-size: 20px; line-height: 40px; }

._table p._p2 { font-size: 18px; line-height: 40px; }

._table ._list { line-height: 34px; font-size: 14px; color: #323232; border-top: 1px solid #333333; border-left: 1px solid #333333; }

._table ._list dl { width: 13%; text-align: center; }

._table ._list ._dl1 { width: 22%; }

._table ._list dt, ._table ._list dd { font-weight: normal; border-bottom: 1px solid #333333; border-right: 1px solid #333333; }

._table ._list dd { height: 34px; font-weight: bold; }

._table ._list dt { background-color: #abd188; }

._table ._list ._dl1 dd { height: auto !important; }

._table ._list dl ._div { width: 50%; }

._table ._list dl ._div ._item { border-bottom: 1px solid #333333; }

._table ._list dl ._div1 { width: 49%; line-height: 102px; border-right: 1px solid #333333; }

._table ._list dl ._height3 { line-height: 104px; }

._table ._list dl ._height2 { line-height: 69px; }

._table ._list dl ._height4 { line-height: 138px; }

._table4 ._list dl ._height4 { line-height: 1.5; height: 138px; }

._table4 ._list dl ._height4 span { padding: 40px 5px 0; display: inline-block; }

._table6 ._title, ._table6 ._list dt { background-color: #F9BF8F !important; }*/
</style>
</head>
<body>
	
	<div class="_wrap" style="width: 1200px; margin: 0 auto;padding: 20px 0 0;">
		<#if (nodata)??>
			<h1>今日数据还未生成</h1>
		<#else>
		
		<h1 style="text-align:center;">万家贷运营数据（${date}）</h1>
		<div class="_table _table1" style="margin-bottom: 20px;">
			<p class="_p2" style="font-size: 18px; line-height: 40px;">1.1 运营数据概览</p>
			<div class="_title" style="text-align: center; line-height: 34px; font-size: 16px; background-color: #fff1d4; border: 1px solid #333333; border-bottom: 0;">${date}运营数据</div>
			<div class="_list" style=" display: flex; display: -webkit-flex; width: 100%; line-height: 34px; font-size: 14px; color: #323232; border-top: 1px solid #333333;">
				<table style="flex: auto; width: 264px; text-align: center; font-weight: bold; border-left: 1px solid #333333; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">数据类型</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">累计用户</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">总投资用户</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">新注册用户</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">新增投资用户</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">新增投资金额（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">新增用户人均投资（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">今日投资用户</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">今日投资金额（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">人均投资金额（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">用户投资收益（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">发布项目数</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">满标项目数【新手标】</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">满标项目数【30~79天】</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">满标项目数【80天以上】</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">满标金额（万元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">满标投资利润（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">红包使用额（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">充值金额（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">成功提现金额（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">满标到期金额（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">平台待收金额（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">资金净流入（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">资金保有量（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; color: #0f02da;border-right: 1px solid #333333; height: 34px;">当月推广渠道累计投资（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333;  color: #0f02da;border-right: 1px solid #333333; height: 34px;">当月总累计投资（元）</td>
						</tr>
					</tbody>
				</table>
				<#list list as item>
				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">
								${item.date}
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								${item.totalUsers}
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								${item.totalInvestUsers}
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								${item.newResiestUsers}
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								${(item.newAvgMapNums)!}
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.newAvgMapTotal)??>${item.newAvgMapTotal?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.newAvgMapAvg)??>${item.newAvgMapAvg?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.newInvestMoneyAvgTotal)??>${item.newInvestMoneyAvgTotal?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.newInvestMoneyAvgTotalmoney)??>${item.newInvestMoneyAvgTotalmoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.newInvestMoneyAvgAvg)??>${item.newInvestMoneyAvgAvg?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.totalProfit)??>${item.totalProfit?string(',###')}</#if>
							</td>
						</tr>
						
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								${item.totalProjects}
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								${item.newUserFullProject}
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								${item.newUserFullProject_count_30}
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								${item.newUserFullProject_count_80}
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.fullMoneyProfitTotalmoney)??>${item.fullMoneyProfitTotalmoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.fullMoneyProfitProfit)??>${item.fullMoneyProfitProfit?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.userRedMoney)??>${item.userRedMoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.chargeMoney)??>${item.chargeMoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.drawMoney)??>${item.drawMoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.fullProjectLimitMoney)??>${item.fullProjectLimitMoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.stayReceiveMoney)??>${item.stayReceiveMoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.gainMoney)??>${item.gainMoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.pollTotalMoney)??>${item.pollTotalMoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.channelInvestMoney)??>${item.channelInvestMoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.monthTotalInvest)??>${item.monthTotalInvest?string(',###')}</#if>
							</td>
						</tr>
					</tbody>
				</table>

				</#list>
				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">
								环比昨日
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<#if y1.totalUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.totalUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.totalUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.totalInvestUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.totalInvestUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.totalInvestUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.newResiestUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.newResiestUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.newResiestUsers}
							</td>
							</#if>
						</tr>

						<tr>
							<#if y1.newAvgMapNums?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.newAvgMapNums}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.newAvgMapNums}
							</td>
							</#if>
						</tr>

						<tr>
							<#if y1.newAvgMapTotal?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.newAvgMapTotal}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.newAvgMapTotal}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.newAvgMapAvg?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.newAvgMapAvg}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.newAvgMapAvg}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.newInvestMoneyAvgTotal?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.newInvestMoneyAvgTotal}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.newInvestMoneyAvgTotal}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.newInvestMoneyAvgTotalmoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.newInvestMoneyAvgTotalmoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.newInvestMoneyAvgTotalmoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.newInvestMoneyAvgAvg?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.newInvestMoneyAvgAvg}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.newInvestMoneyAvgAvg}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.totalProfit?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.totalProfit}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.totalProfit}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.totalProjects?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.totalProjects}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.totalProjects}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.newUserFullProject?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.newUserFullProject}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.newUserFullProject}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.newUserFullProject_count_30?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.newUserFullProject_count_30}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.newUserFullProject_count_30}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.newUserFullProject_count_80?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.newUserFullProject_count_80}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.newUserFullProject_count_80}
							</td>
							</#if>
						</tr>
						
						<tr>
							<#if y1.fullMoneyProfitTotalmoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.fullMoneyProfitTotalmoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.fullMoneyProfitTotalmoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.fullMoneyProfitProfit?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.fullMoneyProfitProfit}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.fullMoneyProfitProfit}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.userRedMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.userRedMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.userRedMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.chargeMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.chargeMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.chargeMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.drawMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.drawMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.drawMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.fullProjectLimitMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.fullProjectLimitMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.fullProjectLimitMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.stayReceiveMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.stayReceiveMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.stayReceiveMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.gainMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.gainMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.gainMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.pollTotalMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.pollTotalMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.pollTotalMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.channelInvestMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.channelInvestMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.channelInvestMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.monthTotalInvest?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y1.monthTotalInvest}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y1.monthTotalInvest}
							</td>
							</#if>
						</tr>
					</tbody>
				</table>
				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">同比前天</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<#if y1.totalUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.totalUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.totalUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.totalInvestUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.totalInvestUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.totalInvestUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.newResiestUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.newResiestUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.newResiestUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.newAvgMapNums?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.newAvgMapNums}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.newAvgMapNums}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.newAvgMapTotal?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.newAvgMapTotal}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.newAvgMapTotal}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.newAvgMapAvg?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.newAvgMapAvg}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.newAvgMapAvg}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.newInvestMoneyAvgTotal?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.newInvestMoneyAvgTotal}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.newInvestMoneyAvgTotal}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.newInvestMoneyAvgTotalmoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.newInvestMoneyAvgTotalmoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.newInvestMoneyAvgTotalmoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.newInvestMoneyAvgAvg?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.newInvestMoneyAvgAvg}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.newInvestMoneyAvgAvg}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.totalProfit?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.totalProfit}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.totalProfit}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.totalProjects?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.totalProjects}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.totalProjects}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.newUserFullProject?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.newUserFullProject}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.newUserFullProject}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.newUserFullProject_count_30?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.newUserFullProject_count_30}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.newUserFullProject_count_30}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.newUserFullProject_count_80?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.newUserFullProject_count_80}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.newUserFullProject_count_80}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.fullMoneyProfitTotalmoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.fullMoneyProfitTotalmoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.fullMoneyProfitTotalmoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.fullMoneyProfitProfit?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.fullMoneyProfitProfit}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.fullMoneyProfitProfit}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.userRedMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.userRedMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.userRedMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.chargeMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.chargeMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.chargeMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.drawMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.drawMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.drawMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.fullProjectLimitMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.fullProjectLimitMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.fullProjectLimitMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.stayReceiveMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.stayReceiveMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.stayReceiveMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.gainMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.gainMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.gainMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.pollTotalMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.pollTotalMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.pollTotalMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y1.channelInvestMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.channelInvestMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.channelInvestMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if y2.monthTotalInvest?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${y2.monthTotalInvest}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${y2.monthTotalInvest}
							</td>
							</#if>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="_table _table2" style="margin-bottom: 20px;">
			<p class="_p2" style="font-size: 18px; line-height: 40px;">1.2未来待收回款数据</p>
			<div class="_title" style="text-align: center; line-height: 34px; font-size: 16px; background-color: #fff1d4; border: 1px solid #333333; border-bottom: 0;">${date}-车贷宝与供应链待收状况</div>

			<div class="_list" style="display: flex; display: -webkit-flex; width: 100%; line-height: 34px; font-size: 14px; color: #323232; border-top: 1px solid #333333;">
				<table style="flex: auto; width: 264px; text-align: center; font-weight: bold; border-left: 1px solid #333333; border-collapse: collapse;">
					<thead>
						<tr>
							<th colspan="2" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">数据类型</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td rowspan="4" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333;">车贷宝（康鑫）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 35px;">投资金额</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">待收利息</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">合计</td>
						</tr>
						<tr>
							<td rowspan="4" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333;">车贷宝（天津）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 35px;">投资金额</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">待收利息</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">合计</td>
						</tr>
						<tr>
							<td rowspan="4" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333;">供应链</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 35px;">投资金额</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">待收利息</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">合计</td>
						</tr>
						<tr>
							<td colspan="2" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">合计</td>
						</tr>
					</tbody>
				</table>
				
				<#list collects as item>
					<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
						<thead>
							<tr>
								<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">${item.date}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
									<#if (item.c_invest)??>${item.c_invest?string(',###')}</#if>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
									<#if (item.c_collect)??>${item.c_collect?string(',###')}</#if>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
									<#if (item.c_tot)??>${item.c_tot?string(',###')}</#if>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
									<#if (item.c_invest)??>${item.cm_invest?string(',###')}</#if>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
									<#if (item.c_collect)??>${item.cm_collect?string(',###')}</#if>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
									<#if (item.c_tot)??>${item.cm_tot?string(',###')}</#if>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
									<#if (item.g_invest)??>${item.g_invest?string(',###')}</#if>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
									<#if (item.g_collect)??>${item.g_collect?string(',###')}</#if>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
									<#if (item.g_tot)??>${item.g_tot?string(',###')}</#if>
								</td>
							</tr>
							<tr>
								<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
									<#if (item.total)??>${item.total?string(',###')}</#if>
								</td>
							</tr>
						</tbody>
					</table>
				</#list>
				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">环比昨日</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<#if wy1.c_invest?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy1.c_invest}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy1.c_invest}
							</td>
							</#if>
						</tr>
						</tr>
						<tr>
							<#if wy1.c_collect?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy1.c_collect}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy1.c_collect}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy1.c_tot?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy1.c_tot}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy1.c_tot}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy1.cm_invest?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy1.cm_invest}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy1.cm_invest}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy1.cm_collect?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy1.cm_collect}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy1.cm_collect}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy1.cm_tot?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy1.cm_tot}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy1.cm_tot}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy1.g_invest?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy1.g_invest}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy1.g_invest}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy1.g_collect?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy1.g_collect}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy1.g_collect}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy1.g_tot?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy1.g_tot}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy1.g_tot}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy1.total?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy1.total}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy1.total}
							</td>
							</#if>
						</tr>
					</tbody>
				</table>
				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">同比前日</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<#if wy2.c_invest?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy2.c_invest}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy2.c_invest}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy2.c_collect?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy2.c_collect}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy2.c_collect}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy2.c_tot?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy2.c_tot}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy2.c_tot}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy2.cm_invest?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy2.cm_invest}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy2.cm_invest}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy2.cm_collect?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy2.cm_collect}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy2.cm_collect}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy2.cm_tot?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy2.cm_tot}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy2.cm_tot}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy2.g_invest?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy2.g_invest}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy2.g_invest}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy2.g_collect?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy2.g_collect}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy2.g_collect}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy2.g_tot?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy2.g_tot}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy2.g_tot}
							</td>
							</#if>
						</tr>
						<tr>
							<#if wy2.total?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${wy2.total}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${wy2.total}
							</td>
							</#if>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="_table _table3" style="margin-bottom: 20px;">
			<p class="_p2" style="font-size: 18px; line-height: 40px;">1.3 用户充值数据</p>
			<div class="_title" style="text-align: center; line-height: 34px; font-size: 16px; background-color: #fff1d4; border: 1px solid #333333; border-bottom: 0;">${date}用户充值数据</div>

			<div class="_list" style="display: flex; display: -webkit-flex; width: 100%; line-height: 34px; font-size: 14px; color: #323232; border-top: 1px solid #333333;">
				<table style="flex: auto; width: 264px; text-align: center; font-weight: bold; border-left: 1px solid #333333; border-collapse: collapse;">
					<thead>
						<tr>
							<th colspan="2" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">数据类型</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td rowspan="3" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333;">推广渠道</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 35px;">充值人数</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">充值金额</td>
						</tr>
						<tr>
							<td rowspan="3" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333;">其他用户</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 35px;">充值人数</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">充值金额</td>
						</tr>
						<tr>
							<td rowspan="5" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333;">合计</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 35px;">充值人数</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">充值金额</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">提现人数</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">提现金额</td>
						</tr>
					</tbody>
				</table>
				
				<#list charges as item>
				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">${item.date}</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.c_count}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;"><#if (item.c_sum)??>${item.c_sum?string(',###')}</#if></td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.o_count}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;"><#if (item.o_sum)??>${item.o_sum?string(',###')}</#if></td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;"><#if (item.chargeTotal)??>${item.chargeTotal?string(',###')}</#if></td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;"><#if (item.otherTotal)??>${item.otherTotal?string(',###')}</#if></td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.w_count}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;"><#if (item.w_sum)??>${item.w_sum?string(',###')}</#if></td>
						</tr>
					</tbody>
				</table>
				</#list>
				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">环比昨日</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<#if cy1.c_count?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy1.c_count}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy1.c_count}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy1.c_sum?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy1.c_sum}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy1.c_sum}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy1.o_count?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy1.o_count}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy1.o_count}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy1.o_sum?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy1.o_sum}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy1.o_sum}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy1.chargeTotal?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy1.chargeTotal}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy1.chargeTotal}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy1.otherTotal?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy1.otherTotal}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy1.otherTotal}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy1.w_count?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy1.w_count}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy1.w_count}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy1.w_sum?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy1.w_sum}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy1.w_sum}
							</td>
							</#if>
						</tr>
					</tbody>
				</table>

				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">同比前天</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<#if cy2.c_count?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy2.c_count}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy2.c_count}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy2.c_sum?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy2.c_sum}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy2.c_sum}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy2.o_count?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy2.o_count}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy2.o_count}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy2.o_sum?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy2.o_sum}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy2.o_sum}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy2.chargeTotal?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy2.chargeTotal}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy2.chargeTotal}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy2.otherTotal?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy2.otherTotal}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy2.otherTotal}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy2.w_count?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy2.w_count}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy2.w_count}
							</td>
							</#if>
						</tr>
						<tr>
							<#if cy2.w_sum?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000">
								${cy2.w_sum}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000">
								${cy2.w_sum}
							</td>
							</#if>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="_table _table4" style="margin-bottom: 20px;">
			<p class="_p1" style="font-size: 20px; line-height: 40px;">二、用户投资数据</p>
			<p class="_p2" style="font-size: 18px; line-height: 40px;">2.1新老用户投资数据</p>

			<div class="_title" style="text-align: center; line-height: 34px; font-size: 16px; background-color: #fff1d4; border: 1px solid #333333; border-bottom: 0;">${date}用户注册数据</div>
			<div class="_list" style="display: flex; display: -webkit-flex; width: 100%; line-height: 34px; font-size: 14px; color: #323232; border-top: 1px solid #333333;">
				<table style="flex: auto; width: 264px; text-align: center; font-weight: bold; border-left: 1px solid #333333; border-collapse: collapse;">
					<thead>
						<tr>
							<th colspan="2" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">数据类型</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td rowspan="4" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333;">新增用户</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 35px;">注册人数</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">投资人数</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">投资金额（元）</td>
						</tr>
						<tr>
							<td rowspan="3" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333;">老用户</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 35px;">复投人数</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">复投金额（元）</td>
						</tr>
						<tr>
							<td colspan="2" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">总投资人数</td>
						</tr>
						<tr>
							<td colspan="2" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">总投资金额（元）</td>
						</tr>
						<tr>
							<td colspan="2" style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">人均投资金额（元）</td>
						</tr>
					</tbody>
				</table>

				<#list newOlds as item>
				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">${item.date}</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.newUsers}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.newInvestUsers}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.newInvestMoney)??>${item.newInvestMoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.newRepeatUsers}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.newRepeatMoney)??>${item.newRepeatMoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.countUsers}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.countInvestMoney)??>${item.countInvestMoney?string(',###')}</#if>
							</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">
								<#if (item.avgMoney)??>${item.avgMoney?string(',###')}</#if>
							</td>
						</tr>
					</tbody>
				</table>
				</#list>

				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">环比昨日</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<#if no1.newUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no1.newUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no1.newUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no1.newInvestUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no1.newInvestUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no1.newInvestUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no1.newInvestMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no1.newInvestMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no1.newInvestMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no1.newRepeatUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no1.newRepeatUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no1.newRepeatUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no1.newRepeatMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no1.newRepeatMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no1.newRepeatMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no1.countUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no1.countUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no1.countUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no1.countInvestMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no1.countInvestMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no1.countInvestMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no1.avgMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no1.avgMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no1.avgMoney}
							</td>
							</#if>
						</tr>
					</tbody>
				</table>

				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">同比前天</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<#if no2.newUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no2.newUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no2.newUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no2.newInvestUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no2.newInvestUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no2.newInvestUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no2.newInvestMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no2.newInvestMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no2.newInvestMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no2.newRepeatUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no2.newRepeatUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no2.newRepeatUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no2.newRepeatMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no2.newRepeatMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no2.newRepeatMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no2.countUsers?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no2.countUsers}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no2.countUsers}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no2.countInvestMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no2.countInvestMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no2.countInvestMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if no2.avgMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${no2.avgMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${no2.avgMoney}
							</td>
							</#if>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="_table _table5" style="margin-bottom: 20px;">
			<p class="_p1" style="font-size: 20px; line-height: 40px;">三、满标及红包明细</p>
			<p class="_p2" style="font-size: 18px; line-height: 40px;">3.1满标及红包概览</p>

			<div class="_title" style="text-align: center; line-height: 34px; font-size: 16px; background-color: #fff1d4; border: 1px solid #333333; border-bottom: 0;">${date}项目数据</div>
			<div class="_list" style="display: flex; display: -webkit-flex; width: 100%; line-height: 34px; font-size: 14px; color: #323232; border-top: 1px solid #333333;">
				<table style="flex: auto; width: 264px; text-align: center; font-weight: bold; border-left: 1px solid #666666; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">数据类型</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">发布项目数</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">满标项目数</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">满标金额（万元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">满标项目利润（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">满标项目毛利润</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">红包使用个数</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">红包使用额（元）</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">投资总金额</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">成本比例</td>
						</tr>
					</tbody>
				</table>
				<#list fullReds as item>
				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">${item.date}</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.totalProjects}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.fullProjectSInfoNums}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.fullProjectSInfoMoney}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.fullProjectSInfoProfit}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;"><#if (item.realProfit)??>${item.realProfit?string(',###')}</#if></td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.redUserInfoNums}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;"><#if (item.redUserInfoMoney)??>${item.redUserInfoMoney?string(',###')}</#if></td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.totalInvest}</td>
						</tr>
						<tr>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px;">${item.baseCostVo}</td>
						</tr>
					</tbody>
				</table>
				
				</#list>

				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">环比昨日</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<#if ry1.totalProjects?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry1.totalProjects}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry1.totalProjects}
							</td>
							</#if>
						</tr>
						<tr>	
							<#if ry1.fullProjectSInfoNums?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry1.fullProjectSInfoNums}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry1.fullProjectSInfoNums}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry1.fullProjectSInfoMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry1.fullProjectSInfoMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry1.fullProjectSInfoMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry1.fullProjectSInfoProfit?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry1.fullProjectSInfoProfit}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry1.fullProjectSInfoProfit}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry1.realProfit?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry1.realProfit}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry1.realProfit}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry1.redUserInfoNums?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry1.redUserInfoNums}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry1.redUserInfoNums}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry1.realProfit?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry1.realProfit}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry1.realProfit}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry1.totalInvest?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry1.totalInvest}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry1.totalInvest}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry1.baseCost?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry1.baseCost}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry1.baseCost}
							</td>
							</#if>
						</tr>
					</tbody>
				</table>
			
				<table style="flex: auto; width: 156px; text-align: center; font-weight: bold; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; background-color: #abd188;">同比前日</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<#if ry2.totalProjects?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry2.totalProjects}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry2.totalProjects}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry2.fullProjectSInfoNums?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry2.fullProjectSInfoNums}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry2.fullProjectSInfoNums}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry2.fullProjectSInfoMoney?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry2.fullProjectSInfoMoney}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry2.fullProjectSInfoMoney}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry2.fullProjectSInfoProfit?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry2.fullProjectSInfoProfit}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry2.fullProjectSInfoProfit}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry2.realProfit?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry2.realProfit}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry2.realProfit}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry2.redUserInfoNums?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry2.redUserInfoNums}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry2.redUserInfoNums}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry2.realProfit?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry2.realProfit}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry2.realProfit}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry2.totalInvest?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry2.totalInvest}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry2.totalInvest}
							</td>
							</#if>
						</tr>
						<tr>
							<#if ry2.baseCost?index_of("-")==0>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #ff0000;">
								${ry2.baseCost}
							</td>
							<#else>
							<td style="border-bottom: 1px solid #333333; border-right: 1px solid #333333; height: 34px; color: #26a000;">
								${ry2.baseCost}
							</td>
							</#if>
						</tr>
					</tbody>
				</table>
			</div>
		</div>


		<div class="_table _table6" style="margin-bottom: 20px;">
			<p class="_p1" style="font-size: 20px; line-height: 40px;">三、满标及红包明细</p>
			<p class="_p2" style="font-size: 18px; line-height: 40px;">3.1满标及红包概览</p>

			<table style=" width: 100%; text-align: center; font-weight: bold; border-left: 1px solid #666666; border-collapse: collapse;">
				<caption style="text-align: center; line-height: 34px; font-size: 16px; background-color: #F9BF8F; border: 1px solid #333333;">${date}红包明细</caption>
				<thead class="_bc3">
					<tr style=" line-height: 34px; background-color: #F9BF8F;">
						<th style="width: 264px; border-bottom: 1px solid #333333; border-right: 1px solid #333333;">红包类型</th>
						<th style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333;">使用面额</th>
						<th style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333;">使用个数</th>
						<th style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333;">金额</th>
						<th style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333;">成本比例</th>
						<th style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333;">投资金额</th>
						<th style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333;">roi</th>
					</tr>
				</thead>
				<tbody>
					<#list reds as item>
					<tr style="line-height: 34px;">
						<td style="width: 264px; border-bottom: 1px solid #333333; border-right: 1px solid #333333; ">${item.redName}</td>
						<td style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333; ">${item.redMoney}</td>
						<td style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333; ">${item.useNum}</td>
						<td style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333; ">${item.redTotalMoney}</td>
						<td style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333; ">${item.costRat}</td>
						<td style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333; ">${item.investMoney}</td>
						<td style="width: 156px; border-bottom: 1px solid #333333; border-right: 1px solid #333333; ">${item.roi}</td>
					</tr>
					</#list>
				</tbody>
			</table>
		</div>
		<h3 style="color:red">(本邮件数据系统自动生成,有任何疑问请联系发件人)</h3>
		</#if>

	</div>

</body>
</html>