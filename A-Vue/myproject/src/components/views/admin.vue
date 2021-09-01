<template>
	<div class="admin">
		<el-container>
			<el-header>
				<div class="welcome">你好，管理员</div>
				<div class="bottons">
					<el-col :span="12">
						<el-dropdown trigger="click">
							<span class="el-dropdown-link">
								操作<i class="el-icon-arrow-down el-icon--right"></i>
							</span>
							<el-dropdown-menu slot="dropdown">
								<el-dropdown-item @click.native="updateDialog=true">修改密码</el-dropdown-item>
								<el-dropdown-item @click.native="backlogin()">退出登录</el-dropdown-item>
							</el-dropdown-menu>
						</el-dropdown>
					</el-col>
				</div>
			</el-header>
			<el-container>
				<el-aside>
					<el-menu :default-active="this.$route.path" class="el-menu-vertical-demo" router>
						<el-menu-item index="/admin/allcompany">
							<i class="el-icon-s-custom"></i>
							<span slot="title">公司管理</span>
						</el-menu-item>
						<el-menu-item index="/admin/allempl">
							<i class="el-icon-user-solid"></i>
							<span slot="title">员工管理</span>
						</el-menu-item>
					</el-menu>
				</el-aside>
				<el-main>
					<router-view></router-view>
				</el-main>
			</el-container>
		</el-container>
		<el-dialog custom-class="mydialog" title="修改密码" :visible.sync="updateDialog">
			<el-form ref="pwdform" :rules="rules" :model="pwdform" label-width="90px">
				<el-row>
					<el-col :span="22">
						<el-form-item label="原密码" prop="oldPwd">
							<el-input v-model="pwdform.oldPwd"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="22">
						<el-form-item label="新密码" prop="newPwd">
							<el-input v-model="pwdform.newPwd"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button type="primary" @click="update('pwdform')">确认</el-button>
				<el-button type="danger" @click="updateDialog = false">取消</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				admin: {},
				updateDialog: false,
				pwdform: {
					oldPwd: '',
					newPwd: ''
				},
				rules: {
					oldPwd: [{
						required: true,
						message: '请输入原密码',
						trigger: 'blur'
					}],
					newPwd: [{
							required: true,
							message: '请输入新密码',
							trigger: 'blur'
						},
						{
							min: 6,
							max: 15,
							message: '长度在6到15位',
							trigger: 'blur'
						}
					]
				},
			};
		},
		methods: {
			backlogin() {
				this.$router.push({
					path: '/'
				});
				this.$message({
					showClose: true,
					type: 'error',
					message: "您已退出登录"
				});
			},
			update(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						if (this.pwdform.oldPwd == this.admin.adPwd) {
							this.axios.get("http://localhost:8888/A-Demo/admin/updatePwd?id=" + this.admin.adId +
								"&pwd=" + this.pwdform.newPwd).then(
								res => {
									this.$message({
										showClose: true,
										type: 'success',
										message: res.data.msg
									});
								});
							this.axios.get("http://localhost:8888/A-Demo/admin/select?id=" + this.$route.query.id)
								.then(
									res => {
										this.admin = res.data.ad;
									});
							this.updateDialog=false;
						} else {
							this.$message({
								showClose: true,
								type: 'error',
								message: "原密码有误"
							});
							return false;
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
			}
		},
		created() {
			this.axios.get("http://localhost:8888/A-Demo/admin/select?id=" + this.$route.query.id).then(
				res => {
					if (res.data.state) {
						this.admin = res.data.ad;
					} else {
						showClose: true,
						this.$message({
							type: 'error',
							message: res.data.msg
						});
						this.$router.push({
							path: '/'
						});
					}
				})
		}
	};
</script>

<style type="text/css" scoped>
	.el-header {
		background-color: #112346;
	}

	.admin,
	.el-container,
	.el-menu-vertical-demo {
		height: 100%;
	}

	.welcome {
		color: #fff;
		float: left;
		padding: 20px;
	}

	.el-dropdown-link {
		color: #fff;
	}

	.bottons {
		padding: 20px 0;
		margin-left: 1400px;
	}
</style>
