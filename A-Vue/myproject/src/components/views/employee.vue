<template>
	<div class="employee">
		<div :class="{'info': myempl, 'allinfo': !myempl }">
			<div class="title">
				个人信息
			</div>
			<table border="1" cellspacing="0" cellpupdateing="0">
				<tr>
					<td width="150" height="40">姓名</td>
					<td width="300">{{employee.emplName}}</td>
				</tr>
				<tr>
					<td height="40">性别</td>
					<td>{{employee.emplSex}}</td>
				</tr>
				<tr>
					<td height="40">邮箱地址</td>
					<td>{{employee.emplEmail}}</td>
				</tr>
				<tr>
					<td height="40">电话</td>
					<td>{{employee.emplTel}}</td>
				</tr>
				<tr>
					<td height="40">年龄</td>
					<td>{{employee.nowAge}}</td>
				</tr>
				<tr>
					<td height="40">职位</td>
					<td>{{employee.position}}</td>
				</tr>
				<tr>
					<td height="40">月薪</td>
					<td>{{employee.salary}}</td>
				</tr>
			</table>
			<div class="title">
				个人简历
			</div>
			<div class="selfcontent">{{employee.content}}</div>
			<div class="title">
				评论列表
			</div>
			<div class="all">
				<evaluate v-for="evaluate in evaluateList" :key="evaluate.evalId" :evaluate="evaluate"
					:compId="$route.query.companyId"></evaluate>
			</div>
		</div>
		<div class="add" v-if="this.$route.query.companyId==this.employee.companyId">
			<div class="left">
				<el-input class="evaluate" type="textarea" :rows="7" placeholder="请输入评论"
					v-model="newevaluate.evalContent"></el-input>
			</div>
			<div class="right">
				<el-button size="mini" type="primary" @click="addeval()">添加评论</el-button>
			</div>
		</div>
	</div>
</template>

<script>
	import evaluate from '../sub/evaluate.vue'

	export default {
		components: {
			evaluate
		},
		data() {
			return {
				myempl: false,
				height: 435,
				employee: {},
				newevaluate: {
					companyId: this.$route.query.companyId,
					resuId: '',
					evalContent: ''
				},
				evaluateList: []
			};
		},
		methods: {
			addeval() {
				this.newevaluate.resuId=this.employee.resuId;
				if (this.newevaluate.evalContent.length == 0) {
					this.$message({
						type: 'error',
						message: '评论内容为空'
					});
				} else {
					this.axios.post("http://localhost:8888/A-Demo/evaluate/add", this.newevaluate).then(
						res => {
							this.$message({
								type: 'success',
								message: res.data.msg
							});
						});
				}
			}
		},
		created() {
			this.axios.get("http://localhost:8888/A-Demo/employee/select?id=" + this.$route.query.id).then(
				res => {
					if (res.data.state) {
						this.employee = res.data.empl;
						if (this.$route.query.companyId == this.employee.companyId) {
							this.myempl = true;
						} else {
							this.myempl = false;
						}
					} else {
						this.$message({
							type: 'error',
							message: res.data.msg
						});
					}
				});
			this.axios.get("http://localhost:8888/A-Demo/evaluate/findByEmplId?id=" + this.$route.query.id).then(
				res => {
					this.evaluateList = res.data;
				});
		}
	};
</script>

<style type="text/css" scoped>
	td {
		padding-left: 20px;
	}

	.info {
		height: 435px;
		overflow-y: scroll;
		overflow-x: hidden;
	}

	.allinfo {
		height: 600px;
		overflow-y: scroll;
		overflow-x: hidden;
	}

	.add {
		position: fixed;
		width: 100%;
		bottom: 0;
		height: 200px;
		border-top: 1px solid #e6e6e6;
		display: block;
	}

	.left {
		position: absolute;
		left: 1%;
		bottom: 11%;
	}

	.right {
		position: absolute;
		right: 22%;
		bottom: 11%;
	}

	.evaluate {
		width: 1080px;
	}

	.title {
		margin: 15px 0;
		font-size: 20px;
	}

	.all {
		width: 495px;
		border-top: 1px solid #808080;
	}

	.selfcontent {
		width: 495px;
	}
</style>
