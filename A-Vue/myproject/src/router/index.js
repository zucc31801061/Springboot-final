import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Register from '@/components/views/register'
import Login from '@/components/views/login'
import Company from '@/components/views/company'
import MyEmpl from '@/components/views/myempl'
import OtherEmpl from '@/components/views/otherempl'
import MyInfo from '@/components/views/myinfo'
import Employee from '@/components/views/employee'
import Admin from '@/components/views/admin'
import AllEmpl from '@/components/views/allempl'
import AllCompany from '@/components/views/allcompany'
import SelectEmpl from '@/components/views/selectempl'

Vue.use(Router)

export default new Router({
	routes: [
		{
			path: '/register',
			name: 'Register',
			component: Register
		},
		{
			path: '/',
			name: 'Login',
			component: Login
		},
		{
			path: '/company/:id',
			name: 'Company',
			component: Company,
			redirect: '/company/:id/myempl',
			children: [{
					path: 'myempl',
					name: 'MyEmpl',
					component: MyEmpl
				},
				{
					path: 'otherempl',
					name: 'OtherEmpl',
					component: OtherEmpl
				},
				{
					path: 'myinfo',
					name: 'MyInfo',
					component: MyInfo
				},
				{
					path: 'employee',
					name: 'Employee',
					component: Employee
				}
			]
		},
		{
			path: '/admin',
			name: 'Admin',
			component: Admin,
			redirect: '/admin/allcompany',
			children: [{
					path: 'allempl',
					name: 'AllEmpl',
					component: AllEmpl
				},
				{
					path: 'allcompany',
					name: 'AllCompany',
					component: AllCompany
				},
				{
					path: 'employee',
					name: 'Employee',
					component: Employee
				},
				{
					path: 'selectEmpl/:id',
					name: 'SelectEmpl',
					component: SelectEmpl
				}
			]
		}
	]
})
