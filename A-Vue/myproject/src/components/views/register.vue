<template>
	<div class="register">
		<div class="register-container">
			<div class="demo-Form col">
				<h3 class="title">用户注册</h3>
				<el-form :model="Form" :rules="rules" ref="Form" label-width="100px" class="demo-ruleForm">
					<el-form-item label="注册类型" prop="kind">
						<el-radio-group v-model="Form.kind">
							<el-radio label="公司"></el-radio>
							<el-radio label="管理员"></el-radio>
						</el-radio-group>
					</el-form-item>
					<el-form-item label="用户名" prop="id">
						<el-input v-model="Form.id"></el-input>
					</el-form-item>
					<el-form-item label="公司名" v-if="Form.kind=='公司'" prop="name">
						<el-input v-model="Form.name"></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="pwd">
						<el-input type="password" v-model="Form.pwd"></el-input>
					</el-form-item>
					<el-form-item label="确认密码" prop="cfpwd">
						<el-input type="password" v-model="Form.cfpwd"></el-input>
					</el-form-item>
					<el-form-item label="邮箱" v-if="Form.kind=='公司'" prop="email">
						<el-input v-model="Form.email"></el-input>
					</el-form-item>
					<el-form-item label="电话" v-if="Form.kind=='公司'" prop="tel">
						<el-input v-model="Form.tel"></el-input>
					</el-form-item>
					<el-form-item label="管理员秘钥" v-if="Form.kind=='管理员'" prop="key">
						<el-input v-model="Form.key"></el-input>
					</el-form-item>
					<el-form-item>
						<el-row>
							<el-col :span="24">
								<div class="grid-content">
									<el-button type="primary" @click="register('Form')">注册</el-button>
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
									<el-link type="success" @click="toLogin">用户登录</el-link>
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
			var validatePassword = (rule, value, callback) => {
				if (value != this.Form.pwd) {
					callback(new Error('确认密码有误'));
				} else {
					callback();
				}
			}
			return {
				Form: {
					kind: '',
					id: '',
					pwd: '',
					cfpwd: '',
					email: '',
					tel: '',
					key: '',
				},
				rules: {
					kind: [{
						required: true,
						message: '请选择注册用户类型',
						trigger: 'blur'
					}],
					id: [{
							required: true,
							message: '请输入用户名',
							trigger: 'blur'
						},
						{
							required: true,
							pattern: /^\d{6,8}$/,
							message: '请输入6~8位数字',
							trigger: 'blur'
						}
					],
					pwd: [{
							required: true,
							message: '请输入密码',
							trigger: 'blur'
						},
						{
							min: 6,
							max: 15,
							message: '长度在6到15位',
							trigger: 'blur'
						}
					],
					cfpwd: [{
						required: true,
						validator: validatePassword,
						trigger: 'blur'
					}],
					name: [{
							required: true,
							message: '请输入公司名',
							trigger: 'blur'
						},
						{
							required: true,
							pattern: /^[\u4e00-\u9fa5]{0,}$/,
							message: '不允许包含除汉字外其他字符',
							trigger: 'blur'
						}
					],
					email: [{
							required: true,
							message: '请输入邮箱地址',
							trigger: 'blur'
						},
						{
							required: true,
							pattern: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
							message: '请输入有效邮箱地址',
							trigger: 'blur'
						}
					],
					tel: [{
							required: true,
							message: '请输入电话号码',
							trigger: 'blur'
						},
						{
							required: true,
							pattern: /^\d{11}$/,
							message: '请输入有效电话号码',
							trigger: 'blur'
						}
					],
					key: [{
							required: true,
							message: '请输入管理员秘钥',
							trigger: 'blur'
						},
						{
							required: true,
							pattern: /admin/,
							message: '您的秘钥有误',
							trigger: 'blur'
						}
					]
				},
				company: {
					companyId: '',
					companyPwd: '',
					companyName: '',
					companyEmail: '',
					companyTel: ''
				},
				admin: {
					adId: '',
					adPwd: ''
				}
			};
		},
		methods: {
			register(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						if (this.Form.kind == '公司') {
							this.company.companyId = this.Form.id;
							this.company.companyPwd = this.Form.pwd;
							this.company.companyName = this.Form.name;
							this.company.companyEmail = this.Form.email;
							this.company.companyTel = this.Form.tel;
							this.axios.post("http://localhost:8888/A-Demo/company/register", this.company).then(
								res => {
									if (res.data.state) {
										this.$router.push({
											path: '/'
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
							this.admin.adId = this.Form.id;
							this.admin.adPwd = this.Form.pwd;
							this.axios.post("http://localhost:8888/A-Demo/admin/register", this.admin).then(
								res => {
									if (res.data.state) {
										this.$router.push({
											path: '/'
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
								message: "未选择注册类型"
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
			toLogin() {
				this.$router.push({
					path: '/'
				});
			}
		}
	}
</script>

<style type="text/css" scoped>
	.register {
		width: 100%;
		height: 722px;
		background-color: #112346;
		text-align: center;
		position: relative;
	}

	.register-container {
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
