<template>
	<div class="login">
		<div class="login-container">
			<div class="demo-Form col">
				<h3 class="title">用户登录</h3>
				<el-form :model="Form" :rules="rules" ref="Form" label-width="100px">
					<el-form-item label="登录类型" prop="kind">
						<el-radio-group v-model="Form.kind">
							<el-radio label="公司"></el-radio>
							<el-radio label="管理员"></el-radio>
						</el-radio-group>
					</el-form-item>
					<el-form-item label="用户名" prop="id">
						<el-input v-model="Form.id"></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="pwd">
						<el-input type="password" v-model="Form.pwd"></el-input>
					</el-form-item>
					<el-form-item>
						<el-row>
							<el-col :span="24">
								<div class="grid-content">
									<el-button type="primary" @click="handlelogin('Form')">登录</el-button>
								</div>
							</el-col>
							<el-col :span="24">
								<div class="grid-content">
									<el-button @click="resetForm('Form')">重置</el-button>
								</div>
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="12">
								<div class="grid-content">
									<el-link type="success" @click="toReg">还未注册</el-link>
								</div>
							</el-col>
						</el-row>
					</el-form-item>
				</el-form>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				Form: {
					kind: '',
					id: '',
					pwd: ''
				},
				rules: {
					kind: [{
						required: true,
						message: '请选择登录用户类型',
						trigger: 'blur'
					}],
					id: [{
						required: true,
						message: '请输入用户名',
						trigger: 'blur'
					}],
					pwd: [{
						required: true,
						message: '请输入密码',
						trigger: 'blur'
					}]
				},
				login: {
					id: '',
					pwd: ''
				}
			};
		},
		methods: {
			handlelogin(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						if (this.Form.kind == '公司') {
							this.axios.post("http://localhost:8888/A-Demo/company/login?id=" + this.Form.id +
								"&pwd=" + this.Form.pwd).then(
								res => {
									if (res.data.state) {
										this.$router.push({
											path: '/company/' + this.Form.id,
										});
										this.$message({
											showClose: true,
											type: 'success',
											message: res.data.msg
										});
									} else {
										this.$message({
											showClose: true,
											type: 'error',
											message: res.data.msg
										});
									}
								})
						} else if (this.Form.kind == '管理员') {
							this.axios.post("http://localhost:8888/A-Demo/admin/login?id=" + this.Form.id +
								"&pwd=" + this.Form.pwd).then(
								res => {
									if (res.data.state) {
										this.$router.push({
											path: '/admin',
											query: {
												id: this.Form.id
											}
										});
										this.$message({
											showClose: true,
											type: 'success',
											message: res.data.msg
										});
									} else {
										this.$message({
											showClose: true,
											type: 'error',
											message: res.data.msg
										});
									}
								})
						} else {
							this.$message({
								showClose: true,
								type: 'warning',
								message: "未选择登录类型"
							});
						}
					} else {
						this.$message({
							showClose: true,
							type: 'warning',
							message: "请输入完整信息"
						});
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			toReg() {
				this.$router.push({
					path: '/register'
				});
			}
		},
		created() {
			this.axios.get("http://localhost:8888/A-Demo/company/addUnemployed");
		}
	}
</script>

<style type="text/css" scoped>
	.login {
		width: 100%;
		height: 722px;
		background-color: #112346;
		text-align: center;
		position: relative;
	}

	.login-container {
		margin: auto;
		border-radius: 10px;
		background: #fff;
		border: 1px solid #eaeaea;
		text-align: left;
		box-shadow: 0 0 20px 2px rgba(0, 0, 0, 0.1);
		display: inline-block;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translateX(-50%) translateY(-50%);
		-webkit-transform: translateX(-50%) translateY(-50%);
	}

	.el-form {
		padding: 0px 70px 0px 35px;
	}

	.title {
		text-align: center;
		color: #505458;
	}

	.el-button {
		width: 100%;
	}

	.el-col {
		width: 50%;
		padding: 5px;
	}

	.el-row {
		margin-left: -60px;
	}
</style>
