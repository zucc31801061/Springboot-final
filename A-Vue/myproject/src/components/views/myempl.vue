<template>
	<div class="myempl">
		<el-table :data="myEmplData" border max-height="580">
			<el-table-column prop="emplName" label="姓名" width="105">
			</el-table-column>
			<el-table-column prop="emplSex" label="性别" width="105">
			</el-table-column>
			<el-table-column prop="emplEmail" label="邮箱" width="160">
			</el-table-column>
			<el-table-column prop="emplTel" label="电话" width="140">
			</el-table-column>
			<el-table-column prop="nowAge" label="年龄" width="105">
			</el-table-column>
			<el-table-column prop="salary" label="薪水" width="105">
			</el-table-column>
			<el-table-column prop="position" label="职位" width="120">
			</el-table-column>
			<el-table-column label="操作" width="310">
				<template slot-scope="scope">
					<el-button size="mini" @click="checkempl(scope.row.emplId)">查看</el-button>
					<el-button size="mini" type="primary" @click="updateempl(scope.row.emplId)">修改</el-button>
					<el-button size="mini" type="danger" @click="dismissEmployee(scope.row.emplId)">辞退</el-button>
				</template>
			</el-table-column>
		</el-table>
		<div class="add">
			<div class="right">
				<el-button size="mini" type="primary" @click="addDialog=true">添加</el-button>
			</div>
		</div>
		<el-dialog custom-class="mydialog" title="添加员工" :visible.sync="addDialog">
			<el-form ref="newEmplData" :rules="rules" :model="newEmplData" label-width="90px">
				<el-row>
					<el-col :span="12">
						<el-form-item label="姓名" prop="emplName">
							<el-input v-model="newEmplData.emplName"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="性别" prop="emplSex">
							<el-radio-group v-model="newEmplData.emplSex">
								<el-radio label="男"></el-radio>
								<el-radio label="女"></el-radio>
							</el-radio-group>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="邮箱地址" prop="emplEmail">
							<el-input v-model="newEmplData.emplEmail"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="电话号码" prop="emplTel">
							<el-input v-model="newEmplData.emplTel"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="8">
						<el-form-item label="年龄" prop="nowAge">
							<el-input v-model="newEmplData.nowAge"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="8">
						<el-form-item label="月薪" prop="salary">
							<el-input v-model="newEmplData.salary"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="8">
						<el-form-item label="职位" prop="position">
							<el-input v-model="newEmplData.position"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="简历" prop="content">
							<el-input type="textarea" :autosize="{ minRows: 4, maxRows: 8}"
								v-model="newEmplData.content"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="resetForm('newEmplData')">重置</el-button>
				<el-button type="primary" @click="add('newEmplData')">确定</el-button>
				<el-button type="danger" @click="addDialog = false">取消</el-button>
			</span>
		</el-dialog>
		<el-dialog custom-class="mydialog" title="修改员工" :visible.sync="updateDialog">
			<el-form ref="updateEmplData" :rules="rules" :model="updateEmplData" label-width="90px">
				<el-row>
					<el-col :span="12">
						<el-form-item label="姓名" prop="emplName">
							<el-input v-model="updateEmplData.emplName"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="性别" prop="emplSex">
							<el-radio-group v-model="updateEmplData.emplSex">
								<el-radio label="男"></el-radio>
								<el-radio label="女"></el-radio>
							</el-radio-group>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="邮箱地址" prop="emplEmail">
							<el-input v-model="updateEmplData.emplEmail"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="电话号码" prop="emplTel">
							<el-input v-model="updateEmplData.emplTel"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="8">
						<el-form-item label="年龄" prop="nowAge">
							<el-input v-model="updateEmplData.nowAge"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="8">
						<el-form-item label="月薪" prop="salary">
							<el-input v-model="updateEmplData.salary"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="8">
						<el-form-item label="职位" prop="position">
							<el-input v-model="updateEmplData.position"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="简历" prop="content">
							<el-input type="textarea" :autosize="{ minRows: 4, maxRows: 8}"
								v-model="updateEmplData.content"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button type="primary" @click="update('updateEmplData')">确认</el-button>
				<el-button type="danger" @click="updateDialog = false">取消</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				addDialog: false,
				updateDialog: false,
				id: '',
				myEmplData: [],
				newEmplData: {
					emplId: '',
					emplName: '',
					emplSex: '',
					emplEmail: '',
					emplTel: '',
					nowAge: '',
					salary: '',
					position: '',
					content: '',
					companyId: '',
					age: ''
				},
				updateEmplData: {},
				rules: {
					emplName: [{
							required: true,
							message: '请输入员工姓名',
							trigger: 'blur'
						},
						{
							required: true,
							pattern: /^[\u4e00-\u9fa5]{0,}$/,
							message: '不允许包含除汉字外其他字符',
							trigger: 'blur'
						}
					],
					emplSex: [{
						required: true,
						message: '请选择员工性别',
						trigger: 'blur'
					}],
					emplEmail: [{
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
					emplTel: [{
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
					nowAge: [{
							required: true,
							message: '请输入员工年龄',
							trigger: 'blur'
						},
						{
							required: true,
							pattern: /^\d{2}$/,
							message: '请输入正确年龄',
							trigger: 'blur'
						}
					],
					salary: [{
							required: true,
							message: '请输入员工月薪',
							trigger: 'blur'
						},
						{
							required: true,
							pattern: /(^\d{0,}$)|(^\d{0,}[\.]{1}[0-9]{1,2}$)/,
							message: '请输入金钱类型0~2位小数',
							trigger: 'blur'
						}
					],
					position: [{
						required: true,
						message: '请输入员工职位',
						trigger: 'blur'
					}],
					content: [{
						required: true,
						message: '请输入员工简历',
						trigger: 'blur'
					}]
				}
			};
		},
		methods: {
			updateempl(emplId) {
				this.axios.get("http://localhost:8888/A-Demo/employee/select?id=" + emplId).then(
					res => {
						if (res.data.state) {
							this.updateEmplData = res.data.empl;
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
			dismissEmployee(emplId) {
				this.axios.get("http://localhost:8888/A-Demo/employee/dismissEmployee?id=" + emplId).then(
					res => {
						if (res.data.state) {
							this.$message({
								showClose: true,
								type: 'success',
								message: res.data.msg
							});
						} else {
							showClose: true,
							this.$message({
								type: 'error',
								message: res.data.msg
							});
						}
					})
			},
			add(formName) {
				this.newEmplData.companyId = this.id;
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.axios.post("http://localhost:8888/A-Demo/employee/add", this.newEmplData).then(
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
			update(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.axios.post("http://localhost:8888/A-Demo/employee/update", this.updateEmplData).then(
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
			checkempl(id) {
				this.$router.push({
					path: '/company/' + this.$route.params.id + '/employee',
					query: {
						id: id,
						companyId: this.$route.params.id
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			}
		},
		created() {
			this.id = this.$route.path.substring(9, this.$route.path.length - 7);
			var _this = this;
			this.axios.get("http://localhost:8888/A-Demo/employee/findByCompId?id=" + this.id).then(
				res => {
					_this.myEmplData = res.data;
				})
		}
	};
</script>

<style type="text/css" scoped>
	.add {
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

	.el-form {
		width: 90%;
	}

	.el-col {
		padding-left: 5px;
	}
</style>

<style>
	.mydialog {
		width: 40%;
		border-radius: 8px;
	}

	.el-dialog__body {
		padding: 10px 20px;
	}

	.el-form-item__label {
		padding-right: 15px;
	}

	.el-menu-item-group__title {
		padding: 0;
		line-height: normal;
		font-size: 12px;
		color: #909399;
	}
</style>
