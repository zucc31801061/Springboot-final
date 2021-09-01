<template>
	<div class="evaluate">
		<div class="name">{{ evaluate && evaluate.companyName }}：</div>
		<div class="content">{{ evaluate.evalContent }}</div>
		<div class="delete" v-if="compId==evaluate.companyId" @click="deleteeval(evaluate.evalId)">删除</div>
		<div class="clear"></div>
	</div>
</template>

<script>
	export default {
		props: {
			evaluate: Object,
			compId: String
		},
		methods: {
			deleteeval(id) {
				this.axios.get("http://localhost:8888/A-Demo/evaluate/deleteEvalById?id=" + id).then(
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
			}
		}
	}
</script>

<style type="text/css" scoped>
	.evaluate {
		width: 92%;
		padding: 20px;
		border-bottom: 1px solid #808080;
	}

	.name {
		color: blue;
		width: 100px;
		float: left;
		overflow: hidden;
	}

	.content {
		margin-left: 110px;
		height: 100%;
	}

	.clear {
		clear: both;
	}

	.delete {
		margin-top: 10px;
		float: right;
		color: red;
		cursor: pointer
	}
</style>
