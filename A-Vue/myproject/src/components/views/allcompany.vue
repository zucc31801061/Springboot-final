<template>
	<div class="allcompany">
		<el-table :data="CompData" border max-height="580">
			<el-table-column prop="companyName" label="公司名" width="250">
			</el-table-column>
			<el-table-column prop="companyEmail" label="邮箱" width="300">
			</el-table-column>
			<el-table-column prop="companyTel" label="电话" width="300">
			</el-table-column>
			<el-table-column label="操作" width="300">
				<template slot-scope="scope">
					<el-button size="mini" @click="selectempl(scope.row.companyId)">查看</el-button>
					<el-button size="mini" type="primary" @click="updatecomp(scope.row.companyId)">修改</el-button>
					<el-button size="mini" type="danger" @click="deletecomp(scope.row.companyId)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
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
				updateDialog: false,
				CompData: [],
				updateCompData: {},
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
		methods: {
			updatecomp(compId) {
				this.axios.get("http://localhost:8888/A-Demo/company/select?id=" + compId).then(
					res => {
						if (res.data.state) {
							this.updateCompData = res.data.comp;
						} else {
							showClose: true,
							this.$message({
								type: 'error',
								message: res.data.msg
							});
						}
					})
				this.updateDialog = true;
			},
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
			},
			checkcomp(id) {
				this.$router.push({
					path: '/admin/company',
					query: {
						id: id
					}
				});
			},
			deletecomp(id) {
				this.$confirm('此操作将永久删除该公司及其发布的评论, 是否继续?', '提示', {
					cancelButtonText: '取消',
					confirmButtonText: '确定',
					type: 'warning'
				}).then(() => {
					this.axios.get("http://localhost:8888/A-Demo/company/delete?id=" + id).then(
						res => {
							if (res.data.state) {
								this.$message({
									type: 'success',
									message: res.data.msg
								});
							} else {
								this.$message({
									type: 'error',
									message: res.data.msg
								});
							}
						})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			selectempl(id){
				this.$router.push({
					path: 'selectEmpl/'+id
				});
			}
		},
		created() {
			var _this = this;
			this.axios.get("http://localhost:8888/A-Demo/company/findAll").then(
				res => {
					_this.CompData = res.data;
				})
		}
	};
</script>

<style type="text/css" scoped>
	.el-form {
		width: 90%;
	}

	.el-col {
		padding-left: 5px;
	}
</style>
