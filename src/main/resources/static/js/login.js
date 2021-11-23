/**
 * Created by zhangyipeng on 2021/06/12.
 */

var login_page = new Vue({
	el: "#login",
	data: {
		login_input_username: "",
		login_input_password: "",
		register_input_username: "",
		register_input_password: "",
		register_input_email: "",
	},
	methods: {
		handleClick() {

		},
		tip(info, style, pos) {
			this.$message({
				message: info,
				type: style,
				offset: pos
			});
		},

		login() {
			var self = this;
			axios.get("login", {
				params: {
					userName: this.login_input_username,
					userPassword: this.login_input_password
				}
			}).then(response => {
				if(response.data.isLogin){
					self.tip(response.data.message, "success", 200);
					setCookie("isLogin", "ok");
					window.location.href = "http://localhost:8080";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		register() {
			var self = this;
			axios.get("register", {
				params: {
					userName: this.register_input_username,
					userPassword: this.register_input_password,
					userEmail: this.register_input_email,
				}
			}).then(response => {
				if(response.data.successfullyRegister){
					self.tip(response.data.message, "success", 200);
					setTimeout(() => {window.location.href = "";}, 1000);
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		}
	}
});


