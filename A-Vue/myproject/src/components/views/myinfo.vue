<template>
	<div class="myinfo">
		<div class="title">
			公司信息
		</div>
		<table border="1" cellspacing="0" cellpupdateing="0">
			<tr>
				<td width="150" height="40">公司名</td>
				<td width="300">{{company.companyName}}</td>
			</tr>
			<tr>
				<td height="40">邮箱</td>
				<td>{{company.companyEmail}}</td>
			</tr>
			<tr>
				<td height="40">电话</td>
				<td>{{company.companyTel}}</td>
			</tr>
		</table>
		<div class="update">
			<div class="right">
				<el-button size="mini" type="primary" @click="updateDialog = true">修改</el-button>
			</div>
		</div>
		<el-dialog custom-class="mydialog" title="修改信息" :visible.sync="updateDialog">
			<el-form ref="updateCompData" :rules="rules" :model="updateCompData" label-width="90px">
				<el-row>
					<el-col :span="22">
						<el-form-item label="公司名" prop="companyName">
							<el-input v-model="updateCompData.companyName"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="22">
						<el-form-item label="邮箱地址" prop="companyEmail">
							<el-input v-model="updateCompData.companyEmail"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="22">
						<el-form-item label="电话" prop="companyTel">
							<el-input v-model="updateCompData.companyTel"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button type="primary" @click="update('updateCompData')">确认</el-button>
				<el-button type="danger" @click="updateDialog = false">取消</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				company: {},
				updateCompData: {},
				updateDialog: false,
				rules: {
					companyName: [{
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
					companyEmail: [{
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
					companyTel: [{
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
					]
				},
			};
		},
		methods:{
			update(formName){
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.axios.post("http://localhost:8888/A-Demo/company/update", this.updateCompData).then(
							res => {
								this.$message({
									showClose: true,
									type: 'success',
									message: res.data.msg
								});
							})
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
			this.axios.get("http://localhost:8888/A-Demo/company/select?id=" + this.$route.params.id).then(
				res => {
					if (res.data.state) {
						this.company = res.data.comp;
						this.updateCompData = res.data.comp;
						console.log(this.company);
					} else {
						this.$message({
							type: 'error',
							message: res.data.msg
						});
					}
				})
		}
	};
</script>

<style type="text/css" scoped>
	td {
		padding-left: 20px;
	}

	.update {
		position: fixed;
		width: 100%;
		bottom: 0;
		height: 56px;
		border-top: 1px solid #e6e6e6;
		display: block;
	}

	.right {
		position: absolute;
		left: 75%;
		top: 50%;
		transform: translateX(-50%) translateY(-50%);
		-webkit-transform: translateX(-50%) translateY(-50%);
	}
	
	.title {
		margin: 15px 0;
		font-size: 20px;
	}
</style>
