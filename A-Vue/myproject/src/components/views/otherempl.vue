<template>
	<div class="otherempl">
		<el-table :data="otherEmplData" border max-height="620">
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
			<el-table-column prop="companyName" label="目前公司" width="160">
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button size="mini" type="primary" @click="recruitEmployee(scope.row.emplId)">招聘</el-button>
					<el-button size="mini" @click="checkempl(scope.row.emplId)">查看</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				otherEmplData: []
			};
		},
		methods: {
			recruitEmployee(emplId) {
				console.log(emplId.toString());
				this.axios.get("http://localhost:8888/A-Demo/employee/recruitEmployee?compId=" + this.id +
					"&emplId=" + emplId).then(
					res => {
						if (res.data.state) {
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
			},
			checkempl(id){
				this.$router.push({
					path: '/company/' + this.$route.params.id + '/employee',
					query: {
						id: id,
						companyId: this.$route.params.id
					}
				});
			}
		},
		created() {
			this.id = this.$route.path.substring(9, this.$route.path.length - 10);
			var _this = this;
			this.axios.get("http://localhost:8888/A-Demo/employee/findOther?id=" + this.id).then(
				res => {
					_this.otherEmplData = res.data;
				})
		}
	};
</script>

<style type="text/css" scoped>

</style>
