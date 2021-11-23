/**
 * Created by zhangyipeng on 2021/06/12.
 */

var index_page = new Vue({
	el: "#index",
	data: {
		pickerOptions: {
			disabledDate(time) {
				return time.getTime() > Date.now();
			}
		},

		logoutDialogVisible: false,

		addAnimalDialogFormVisible: false,
		addSpeciesDialogFormVisible: false,
		addAreaDialogFormVisible: false,
		addFoodDialogFormVisible: false,
		addFeedDialogFormVisible: false,
		addWorkerDialogFormVisible: false,
		addDistrictDialogFormVisible: false,
		addTeamDialogFormVisible: false,
		addTherapyDialogFormVisible: false,
		addStockDialogFormVisible: false,

		newAnimalName: "",
		newAnimalAge: 0,
		newAnimalGender: "",
		newAnimalSpeciesName: "",
		newAnimalHealthy: "",
		newAnimalDistrictName: "",

		newFoodName: "",
		newFoodUnit: "",

		newStockFoodName: "",
		newStockAmount: "",
		newStockCost: "",
		newStockTime: "",
		newStockExpireTime: "",

		newSpeciesName: "",
		newSpeciesLife: 0,
		newSpeciesLevel: "",
		newSpeciesHabit: "",
		newSpeciesHabitat: "",

		newWorkerName: "",
		newWorkerAge: 0,
		newWorkerGender: "",
		newWorkerWorkingAge: 0,
		newWorkerTeamName: "",
		newWorkerExperienced: "",
		newWorkerVet: "",

		newAreaName: "",

		newDistrictName: "",
		newDistrictAreaName: "",

		newTeamName: "",
		newTeamCaptionID: "无",
		newTeamAreaName: "",

		newTherapyAnimalId: "",
		newTherapyWorkerId: "",
		newTherapyDescription: "",
		newTherapyStartTime: "",
		newTherapyEndTime: "",

		newFeedAnimalId: "",
		newFeedFoodName: "",
		newFeedAppetite: "",
		newFeedDate: "",

		updateAnimalDialogFormVisible: false,
		updateSpeciesDialogFormVisible: false,
		updateWorkerDialogFormVisible: false,
		updateAreaDialogFormVisible: false,
		updateDistrictDialogFormVisible: false,
		updateTeamDialogFormVisible: false,
		updateTherapyDialogFormVisible: false,
		updateFoodDialogFormVisible: false,
		updateFeedDialogFormVisible: false,

		updateAnimalId: 0,
		updateAnimalName: "",
		updateAnimalAge: 0,
		updateAnimalGender: "",
		updateAnimalSpeciesName: "",
		updateAnimalHealthy: "",
		updateAnimalDistrictName: "",

		updateSpeciesId: 0,
		updateSpeciesName: "",
		updateSpeciesLife: 0,
		updateSpeciesLevel: "",
		updateSpeciesHabit: "",
		updateSpeciesHabitat: "",

		updateNewWorkerId: 0,
		updateNewWorkerName: "",
		updateNewWorkerAge: 0,
		updateNewWorkerGender: "",
		updateNewWorkerWorkingAge: 0,
		updateNewWorkerTeamName: "",
		updateNewWorkerExperienced: "",
		updateNewWorkerVet: "",
		updateOldWorkerName: "",
		updateOldWorkerAge: 0,
		updateOldWorkerGender: "",
		updateOldWorkerWorkingAge: 0,
		updateOldWorkerTeamId: 0,
		updateOldWorkerExperienced: "",
		updateOldWorkerVet: "",

		updateNewAreaId: 0,
		updateNewAreaName: "",
		updateOldAreaName: "",


		updateFoodId: 0,
		updateFoodName: "",
		updateFoodUnit: "",
		updateFoodStorage: "",

		updateDistrictId: 0,
		updateDistrictName: "",
		updateDistrictAreaId: 0,
		updateDistrictAreaName: "",

		updateTeamId: 0,
		updateTeamName: "",
		updateTeamCaptionId: 0,
		updateTeamAreaName: "",

		updateFeedId: 0,
		updateFeedAnimalId: "",
		updateFeedFoodName: "",
		updateFeedAppetite: "",
		updateFeedDate: "",

		updateTherapyId: 0,
		updateTherapyAnimalId: "",
		updateTherapyWorkerId: "",
		updateTherapyDescription: "",
		updateTherapyStartTime: "",
		updateTherapyEndTime: "",
		
		deleteAnimalDialogFormVisible: false,		
		deleteSpeciesDialogFormVisible: false,
		deleteWorkerDialogFormVisible: false,
		deleteAreaDialogFormVisible: false,
		deleteFoodDialogFormVisible: false,
		deleteFeedDialogFormVisible: false,
		deleteDistrictDialogFormVisible: false,
		deleteTeamDialogFormVisible: false,
		deleteTherapyDialogFormVisible: false,

		deleteAnimalId: 0,
		deleteSpeciesId: 0,
		deleteWorkerId: 0,
		deleteAreaId: 0,
		deleteFoodId: 0,
		deleteFeedId: 0,
		deleteDistrictId: 0,
		deleteTeamId: 0,
		deleteTherapyId: 0,

		animalTableData: [],
		speciesTableData: [],
		workerTableData: [],
		areaTableData: [],
		foodTableData: [],
		stockTableData: [],
		districtTableData: [],
		teamTableData: [],
		therapyTableData: [],
		feedTableData: [],


		animal_gender_options: ["雌", "雄"],
		gender_options: ["男", "女"],
		healthy_options: ["true", "false"],
		experienced_options: ["true", "false"],
		vet_options: ["true", "false"],
		species_options: [],
		area_options: [],
		caption_options: [],
		vet_worker_options: [],
		animal_options: [],
		team_options: [],
		district_options: [],
		food_options: [],

		max_animal_species_num: 50,
		max_food_special_num: 30,
		animal_species_group: [],
		food_species_group:[]
	},


	methods: {
		handleClick() {

		},
		handleClose() {

		},
		tip(info, style, pos) {
			this.$message({
				message: info,
				type: style,
				offset: pos
			});
		},

		addAnimal() {
			var self = this;
			axios.get("addAnimal", {
				params: {
					name: this.newAnimalName,
					age: this.newAnimalAge,
					gender: this.newAnimalGender,
					speciesName: this.newAnimalSpeciesName,
					healthy: this.newAnimalHealthy === "是",
					districtName: this.newAnimalDistrictName
				}
			}).then(response => {
				if(response.data.successfullyAdd){
					self.tip(response.data.message, "success", 200);
					self.getAllAnimal();
					self.addAnimalDialogFormVisible = false;
					self.newAnimalName = "";
					self.newAnimalAge = 0;
					self.newAnimalGender = "";
					self.newAnimalSpeciesName = "";
					self.newAnimalHealthy = "";
					self.newAnimalDistrictName = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});	
		},

		addSpecies() {
			var self = this;
			axios.get("addSpecies", {
				params: {
					name: this.newSpeciesName,
					life: this.newSpeciesLife,
					level: this.newSpeciesLevel,
					habit: this.newSpeciesHabit,
					habitat: this.newSpeciesHabitat
				}
			}).then(response => {
				if(response.data.successfullyAdd){
					self.tip(response.data.message, "success", 200);
					self.getAllSpecies();
					self.addSpeciesDialogFormVisible = false;
					self.newSpeciesName = "";
					self.newSpeciesLife = 0;
					self.newSpeciesLevel = "";
					self.newSpeciesHabit = "";
					self.newSpeciesHabitat = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});	
		},

		addWorker() {
			var self = this;
			axios.get("addWorker", {
				params: {
					name: this.newWorkerName,
					age: this.newWorkerAge,
					gender: this.newWorkerGender,
					workingAge: this.newWorkerWorkingAge,
					teamName: this.newWorkerTeamName,
					experienced: this.newWorkerExperienced === "是",
					vet: this.newWorkerVet === "是",
				}
			}).then(response => {
				if(response.data.successfullyAdd){
					self.tip(response.data.message, "success", 200);
					self.getAllWorker();
					self.addWorkerDialogFormVisible = false;
					self.newWorkerName = "";
					self.newWorkerAge = 0;
					self.newWorkerGender = "";
					self.newWorkerWorkingAge = 0;
					self.newWorkerTeamName = "";
					self.newWorkerExperienced = "";
					self.newWorkerVet = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		addTeam() {
			var self = this;
			axios.get("addTeam", {
				params: {
					name: this.newTeamName,
					captionId: this.newTeamCaptionID === "无" ? 0 : parseInt(this.newTeamCaptionID),
					areaName: this.newTeamAreaName,
				}
			}).then(response => {
				if(response.data.successfullyAdd){
					self.tip(response.data.message, "success", 200);
					self.getAllTeam();
					self.addTeamDialogFormVisible = false;
					self.newTeamName = "";
					self.newDistrictAreaName = "";
					self.newTeamCaptionID = 0;
					self.newTeamAreaName = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});	
		},

		addArea() {
			var self = this;
			axios.get("addArea", {
				params: {
					areaName: this.newAreaName
				}
			}).then(response => {
				if(response.data.successfullyAdd){
					self.tip(response.data.message, "success", 200);
					self.getAllArea();
					self.addAreaDialogFormVisible = false;
					newAreaName = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		addFood() {
			var self = this;
			axios.get("addFood", {
				params: {
					name: this.newFoodName,
					unit: this.newFoodUnit
				}
			}).then(response => {
				if(response.data.successfullyAdd){
					self.tip(response.data.message, "success", 200);
					self.getAllFood();
					self.addFoodDialogFormVisible = false;
					newFoodName = "";
					newFoodUnit = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		addFeed() {
			var self = this;
			axios.get("addFeedRecord", {
				params: {
					foodName: this.newFeedFoodName,
					animalId: this.newFeedAnimalId,
					appetite: this.newFeedAppetite,
					date: this.newFeedDate
				}
			}).then(response => {
				if(response.data.successfullyAdd){
					self.tip(response.data.message, "success", 200);
					self.getAllFeed();
					self.addFeedDialogFormVisible = false;
					self.newFeedFoodName = "";
					self.newFeedAnimalId = "";
					self.newFeedAppetite = "";
					self.newFeedDate = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		addStock(name) {
			this.addStockDialogFormVisible = true;
			this.newStockFoodName = name;
		},

		confirmStock() {
			var self = this;
			axios.get("addStockRecord", {
				params: {
					foodName: this.newStockFoodName,
					amount: this.newStockAmount,
					cost: this.newStockCost,
					time: this.newStockTime,
					expireTime: this.newStockExpireTime
				}
			}).then(response => {
				if(response.data.successfullyAdd){
					self.tip(response.data.message, "success", 200);
					self.getAllFood();
					self.addStockDialogFormVisible = false;
					newStockFoodName = "";
					newStockAmount = "";
					newStockCost = "";
					newStockTime = "";
					newStockExpireTime = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		addDistrict() {
			var self = this;
			axios.get("addDistrict", {
				params: {
					name: this.newDistrictName,
					areaName: this.newDistrictAreaName
				}
			}).then(response => {
				if(response.data.successfullyAdd){
					self.tip(response.data.message, "success", 200);
					self.getAllDistrict();
					self.addDistrictDialogFormVisible = false;
					self.newDistrictName = "";
					self.newDistrictAreaName = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});			
		},

		addTherapy() {
			var self = this;
			axios.get("addTherapy", {
				params: {
					stringAnimalId: this.newTherapyAnimalId,
					stringWorkerId: this.newTherapyWorkerId,
					description: this.newTherapyDescription,
					startTime: this.newTherapyStartTime,
					endTime: this.newTherapyEndTime
				}
			}).then(response => {
				if(response.data.successfullyAdd){
					self.tip(response.data.message, "success", 200);
					self.getAllTherapy();
					self.addTherapyDialogFormVisible = false;
					self.newTherapyAnimalId = "";
					self.newTherapyWorkerId = "";
					self.newTherapyDescription = "";
					self.newTherapyStartTime = "";
					self.newTherapyEndTime = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});	
		},

		getAllAnimal() {
			var self = this;
			axios.get("getAllAnimal", {
				params: {
				}
			}).then(response => {
				var animal_list = response.data.animalList;
				this.animalTableData = [];
				this.animal_options = [];
				var tmp_animal_group = [];
				for(var i=0;i<this.max_animal_species_num;i++){
					tmp_animal_group.push({
						id: i,
						num: 0,
						name: ""
					});
				}
				for(var i=0;i<animal_list.length;i++){
					this.animal_options.push(animal_list[i].id);
					this.animalTableData.push({
						id: animal_list[i].id,
						name: animal_list[i].name,
						age: animal_list[i].age,
						gender: animal_list[i].gender,
						species_name: animal_list[i].speciesName,
						healthy: animal_list[i].healthy,
						district_name: animal_list[i].districtName
					});
					tmp_animal_group[animal_list[i].speciesId].num++;
					tmp_animal_group[animal_list[i].speciesId].name = animal_list[i].speciesName;
				}
				this.animal_species_group = [];
				for(var i=0;i<this.max_animal_species_num;i++){
					if(tmp_animal_group[i].num > 0){
						this.animal_species_group.push(tmp_animal_group[i]);
					}
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		getAllSpecies() {
			var self = this;
			axios.get("getAllSpecies", {
				params: {
				}
			}).then(response => {
				var species_list = response.data.speciesList;
				this.speciesTableData = [];
				this.species_options = [];
				for(var i=0;i<species_list.length;i++){
					this.species_options.push(species_list[i].name);
					this.speciesTableData.push({
						id: species_list[i].id,
						name: species_list[i].name,
						life: species_list[i].life,
						level: species_list[i].level,
						habit: species_list[i].habit,
						habitat: species_list[i].habitat
					});
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		getAllWorker() {
			var self = this;
			axios.get("getAllWorker", {
				params: {
				}
			}).then(response => {
				var worker_list = response.data.workerList;
				this.workerTableData = [];
				this.vet_worker_options = [];
				this.caption_options = ["无"];
				for(var i=0;i<worker_list.length;i++){
					if(worker_list[i].id == 0)continue;
					if(worker_list[i].vet)
					this.vet_worker_options.push(worker_list[i].id);
					this.caption_options.push(worker_list[i].id);
					this.workerTableData.push({
						id: worker_list[i].id,
						name: worker_list[i].name,
						age: worker_list[i].age,
						gender: worker_list[i].gender,
						working_age: worker_list[i].workingAge,
						team_name: worker_list[i].teamName,
						experienced: worker_list[i].experienced,
						vet: worker_list[i].vet
					});
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		getAllTeam() {
			var self = this;
			axios.get("getAllTeam", {
				params: {
				}
			}).then(response => {
				var team_list = response.data.teamList;
				this.teamTableData = [];
				this.team_options = [];
				for(var i=0;i<team_list.length;i++){
					this.team_options.push(team_list[i].name);
					if(team_list[i].id == 0)continue;
					this.teamTableData.push({
						id: team_list[i].id,
						name: team_list[i].name,
						caption_id: team_list[i].captionId,
						caption_name: team_list[i].captionName,
						area_name: team_list[i].areaName
					});
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		getAllDistrict() {
			var self = this;
			axios.get("getAllDistrict", {
				params: {
				}
			}).then(response => {
				var district_list = response.data.districtList;
				this.districtTableData = [];
				this.district_options = [];
				for(var i=0;i<district_list.length;i++){
					this.district_options.push(district_list[i].name);
					if(district_list[i].id == 0)continue;
					this.districtTableData.push({
						id: district_list[i].id,
						name: district_list[i].name,
						area_name: district_list[i].areaName
					});
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		getAllArea() {
			var self = this;
			axios.get("getAllArea", {
				params: {
				}
			}).then(response => {
				var area_list = response.data.areaList;
				this.areaTableData = [];
				this.area_options = [];
				for(var i=0;i<area_list.length;i++){
					this.area_options.push(area_list[i].areaName);
					if(area_list[i].id == 0)continue;
					this.areaTableData.push({
						id: area_list[i].id,
						name: area_list[i].areaName
					});
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		getAllFood() {
			var self = this;
			axios.get("getAllFood", {
				params: {
				}
			}).then(response => {
				var food_list = response.data.foodList;
				this.foodTableData = [];
				for(var i=0;i<food_list.length;i++){
					if(food_list[i].id == 0)continue;
					this.food_options.push(food_list[i].name);
					this.foodTableData.push({
						id: food_list[i].id,
						name: food_list[i].name,
						unit: food_list[i].unit,
						storage: food_list[i].storage
					});
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		getAllFeed() {
			var self = this;
			axios.get("queryAllFeedRecord", {
				params: {
				}
			}).then(response => {
				var feed_list = response.data.feedRecordList;
				this.feedTableData = [];
				for(var i=0;i<feed_list.length;i++){
					this.feedTableData.push({
						id: feed_list[i].id,
						animal_id: feed_list[i].animalId,
						animal_name: feed_list[i].animalName,
						food_name: feed_list[i].foodName,
						appetite: feed_list[i].appetite,
						date: feed_list[i].feedTime,
						species: feed_list[i].species
					});
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		getAllTherapy() {
			var self = this;
			axios.get("getAllTherapy", {
				params: {
				}
			}).then(response => {
				var therapy_list = response.data.therapyList;
				this.therapyTableData = [];
				for(var i=0;i<therapy_list.length;i++){
					this.therapyTableData.push({
						id: therapy_list[i].id,
						animal_id: therapy_list[i].animalId,
						animal_name: therapy_list[i].animalName,
						species_name: therapy_list[i].speciesName,
						worker_id: therapy_list[i].workerId,
						worker_name: therapy_list[i].workerName,
						description: therapy_list[i].description,
						start_time: therapy_list[i].startTime,
						end_time: therapy_list[i].endTime.slice(0,10)
					});
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		getAllStock() {
			var self = this;
			axios.get("queryAllStockRecord", {
				params: {
				}
			}).then(response => {
				var stock_list = response.data.stockRecordList;
				this.stockTableData = [];
				var tmp_stock_group = [];
				for(var i=0;i<this.max_food_special_num;i++){
					tmp_stock_group.push({
						id: i,
						sum: 0.0,
						name: ""
					});
				}
				for(var i=0;i<stock_list.length;i++){
					this.stockTableData.push({
						id: stock_list[i].id,
						food_name: stock_list[i].foodName,
						amount: stock_list[i].amount,
						cost: stock_list[i].cost,
						time: stock_list[i].time,
						expire_time: stock_list[i].expireTime,
					});
					tmp_stock_group[stock_list[i].foodId].sum += stock_list[i].cost;
					tmp_stock_group[stock_list[i].foodId].name = stock_list[i].foodName;
				}
				for(var i=0;i<this.max_food_special_num;i++){
					if(tmp_stock_group[i].sum > 0){
						this.food_species_group.push(tmp_stock_group[i]);
					}
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		updateAnimal(id, name, age, gender, species_name, healthy, district_name) {
			this.updateAnimalDialogFormVisible = true;
			this.updateAnimalId = id;
			this.updateAnimalName = name;
			this.updateAnimalAge = age;
			this.updateAnimalGender = gender;
			this.updateAnimalSpeciesName = species_name;
			this.updateAnimalHealthy = healthy ? "是" :"否";
			this.updateAnimalDistrictName = district_name;
		},

		updateSpecies(id, name, life, level, habit, habitat) {
			this.updateSpeciesDialogFormVisible = true;
			this.updateSpeciesId = id;
			this.updateSpeciesName = name;
			this.updateSpeciesLife = life;
			this.updateSpeciesLevel = level;
			this.updateSpeciesHabit = habit;
			this.updateSpeciesHabitat = habitat;
		},

		updateWorker(id, name, age, gender, working_age, team_name, experienced, vet) {
			this.updateWorkerDialogFormVisible = true;
			this.updateNewWorkerId = id;
			this.updateNewWorkerName = name;
			this.updateNewWorkerAge = age;
			this.updateNewWorkerGender = gender;
			this.updateNewWorkerWorkingAge = ""+working_age;
			this.updateNewWorkerTeamName = team_name;
			this.updateNewWorkerExperienced = experienced ? "是" : "否";
			this.updateNewWorkerVet = vet ? "是" : "否";
		},

		updateTeam(id, name, caption_id, area_name) {
			this.updateTeamDialogFormVisible = true;
			this.updateTeamId = id;
			this.updateTeamName = name;
			this.updateTeamCaptionId = caption_id;
			this.updateTeamAreaName = area_name;
		},

		updateArea(id, name) {
			this.updateAreaDialogFormVisible = true;
			this.updateNewAreaId = id;
			this.updateOldAreaName = name;
		},

		updateFood(id, name, unit, storage) {
			this.updateFoodDialogFormVisible = true;
			this.updateFoodId = id;
			this.updateFoodName = name;
			this.updateFoodUnit = unit;
			this.updateFoodStorage = storage;
		},

		updateFeed(id, animal_id, food_name,appetite,date) {
			this.updateFeedDialogFormVisible = true;
			this.updateFeedId = id;
			this.updateFeedAnimalId = animal_id;
			this.updateFeedFoodName = food_name;
			this.updateFeedAppetite = appetite;
			this.updateFeedDate = date;
		},

		updateDistrict(id, name, area_name) {
			this.updateDistrictDialogFormVisible = true;
			this.updateDistrictId = id;
			this.updateDistrictName = name;
			this.updateDistrictAreaName = area_name;
		},

		updateTherapy(id, animal_id, worker_id, description, start_time, end_time) {
			this.updateTherapyDialogFormVisible = true;
			this.updateTherapyId = id;
			this.updateTherapyAnimalId = animal_id;
			this.updateTherapyWorkerId = worker_id;
			this.updateTherapyDescription = description;
			this.updateTherapyStartTime = start_time;
			this.updateTherapyEndTime = end_time;
		},

		confirmUpdateAnimal() {
			var self = this;
			axios.get("updateAnimal", {
				params: {
					id: self.updateAnimalId,
					name: self.updateAnimalName,
					age: self.updateAnimalAge,
					gender: self.updateAnimalGender,
					speciesName: self.updateAnimalSpeciesName,
					healthy: self.updateAnimalHealthy === "是",
					districtName: self.updateAnimalDistrictName
				}
			}).then(response => {
				if(response.data.successfullyUpdate){
					self.tip(response.data.message, "success", 200);
					self.getAllAnimal();
					self.updateAnimalDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmUpdateSpecies() {
			var self = this;
			axios.get("updateSpecies", {
				params: {
					id: self.updateSpeciesId,
					name: self.updateSpeciesName,
					life: self.updateSpeciesLife,
					level: self.updateSpeciesLevel,
					habit: self.updateSpeciesHabit,
					habitat: self.updateSpeciesHabitat
				}
			}).then(response => {
				if(response.data.successfullyUpdate){
					self.tip(response.data.message, "success", 200);
					self.getAllSpecies();
					self.updateSpeciesDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmUpdateWorker() {
			var self = this;
			axios.get("updateWorker", {
				params: {
					id: self.updateNewWorkerId,
					name: self.updateNewWorkerName,
					age: self.updateNewWorkerAge,
					gender: self.updateNewWorkerGender,
					workingAge: parseInt(self.updateNewWorkerWorkingAge),
					teamName: self.updateNewWorkerTeamName,
					experienced: self.updateNewWorkerExperienced === "是",
					vet: self.updateNewWorkerVet === "是",

				}
			}).then(response => {
				if(response.data.successfullyUpdate){
					self.tip(response.data.message, "success", 200);
					self.getAllWorker();
					self.updateWorkerDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},


		confirmUpdateTeam() {
			var self = this;
			axios.get("updateTeam", {
				params: {
					id: self.updateTeamId,
					name: self.updateTeamName,
					captionId: self.updateTeamCaptionId,
					areaName: self.updateTeamAreaName
				}
			}).then(response => {
				if(response.data.successfullyUpdate){
					self.tip(response.data.message, "success", 200);
					self.getAllTeam();
					self.updateTeamDialogFormVisible = false;
					self.updateTeamId = 0;
					self.updateTeamName = "";
					self.updateTeamCaptionId = 0;
					self.updateTeamAreaName = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});			
		},

		confirmUpdateArea() {
			var self = this;
			axios.get("updateArea", {
				params: {
					id: self.updateNewAreaId,
					newAreaName: self.updateNewAreaName
				}
			}).then(response => {
				if(response.data.successfullyUpdate){
					self.tip(response.data.message, "success", 200);
					self.updateOldAreaName = "";
					self.getAllArea();
					self.updateAreaDialogFormVisible = false;
					self.updateNewAreaName = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmUpdateFood() {
			var self = this;
			axios.get("updateFood", {
				params: {
					id: self.updateFoodId,
					name: self.updateFoodName,
					unit: self.updateFoodUnit,
					storage: self.updateFoodStorage
				}
			}).then(response => {
				if(response.data.successfullyUpdate){
					self.tip(response.data.message, "success", 200);
					self.getAllFood();
					self.updateFoodDialogFormVisible = false;
					self.updateFoodName = "";
					self.updateFoodUnit = "";
					self.updateFoodStorage = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmUpdateDistrict() {
			var self = this;
			axios.get("updateDistrict", {
				params: {
					id: self.updateDistrictId,
					name: self.updateDistrictName,
					areaName: self.updateDistrictAreaName
				}
			}).then(response => {
				if(response.data.successfullyUpdate){
					self.tip(response.data.message, "success", 200);
					self.getAllDistrict();
					self.updateDistrictDialogFormVisible = false;
					self.updateDistrictId = "";
					self.updateDistrictName = "";
					self.updateDistrictAreaName = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmUpdateFeed() {
			var self = this;
			axios.get("updateFeed", {
				params: {
					id: self.updateFeedId,
					stringAnimalId: self.updateFeedAnimalId,
					foodName: self.updateFeedFoodName,
					appetite: self.updateFeedAppetite,
					date: self.updateFeedDate
				}
			}).then(response => {
				if(response.data.successfullyUpdate){
					self.tip(response.data.message, "success", 200);
					self.getAllFeed();
					self.updateFeedDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmUpdateTherapy() {
			var self = this;
			axios.get("updateTherapy", {
				params: {
					id: self.updateTherapyId,
					stringAnimalId: self.updateTherapyAnimalId,
					stringWorkerId: self.updateTherapyWorkerId,
					description: self.updateTherapyDescription,
					startTime: self.updateTherapyStartTime,
					endTime: self.updateTherapyEndTime,
				}
			}).then(response => {
				if(response.data.successfullyUpdate){
					self.tip(response.data.message, "success", 200);
					self.getAllTherapy();
					self.updateTherapyDialogFormVisible = false;
					self.updateTherapyId = 0;
					self.updateTherapyAnimalId = "";
					self.updateTherapyWorkerId = "";
					self.updateTherapyDescription = "";
					self.updateTherapyStartTime = "";
					self.updateTherapyEndTime = "";
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		deleteAnimal(id) {
			this.deleteAnimalDialogFormVisible = true;
			this.deleteAnimalId = id;
		},

		deleteSpecies(id) {
			this.deleteSpeciesDialogFormVisible = true;
			this.deleteSpeciesId = id;
		},

		deleteWorker(id) {
			this.deleteWorkerDialogFormVisible = true;
			this.deleteWorkerId = id;
		},

		deleteTeam(id) {
			this.deleteTeamDialogFormVisible = true;
			this.deleteTeamId = id;
		},

		deleteArea(id) {
			this.deleteAreaDialogFormVisible = true;
			this.deleteAreaId = id;
		},

		deleteFood(id) {
			this.deleteFoodDialogFormVisible = true;
			this.deleteFoodId = id;
		},

		deleteFeed(id) {
			this.deleteFeedDialogFormVisible = true;
			this.deleteFeedId = id;
		},

		deleteDistrict(id) {
			this.deleteDistrictDialogFormVisible = true;
			this.deleteDistrictId = id;
		},

		deleteTherapy(id) {
			this.deleteTherapyDialogFormVisible = true;
			this.deleteTherapyId = id;
		},

		confirmDeleteAnimal() {
			var self = this;
			axios.get("deleteAnimal", {
				params: {
					id: self.deleteAnimalId
				}
			}).then(response => {
				if(response.data.successfullyDelete){
					self.tip(response.data.message, "success", 200);
					self.getAllAnimal();
					self.deleteAnimalDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmDeleteSpecies() {
			var self = this;
			axios.get("deleteSpecies", {
				params: {
					id: self.deleteSpeciesId
				}
			}).then(response => {
				if(response.data.successfullyDelete){
					self.tip(response.data.message, "success", 200);
					self.getAllSpecies();
					self.deleteSpeciesDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmDeleteWorker() {
			var self = this;
			axios.get("deleteWorker", {
				params: {
					id: self.deleteWorkerId
				}
			}).then(response => {
				if(response.data.successfullyDelete){
					self.tip(response.data.message, "success", 200);
					self.getAllWorker();
					self.deleteWorkerDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmDeleteTeam() {
			var self = this;
			axios.get("deleteTeam", {
				params: {
					id: self.deleteTeamId
				}
			}).then(response => {
				if(response.data.successfullyDelete){
					self.tip(response.data.message, "success", 200);
					self.getAllTeam();
					self.deleteTeamDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmDeleteArea() {
			var self = this;
			axios.get("deleteArea", {
				params: {
					id: self.deleteAreaId
				}
			}).then(response => {
				if(response.data.successfullyDelete){
					self.tip(response.data.message, "success", 200);
					self.getAllArea();
					self.deleteAreaDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmDeleteFood() {
			var self = this;
			axios.get("deleteFood", {
				params: {
					id: self.deleteFoodId
				}
			}).then(response => {
				if(response.data.successfullyDelete){
					self.tip(response.data.message, "success", 200);
					self.getAllFood();
					self.deleteFoodDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmDeleteFeed() {
			var self = this;
			axios.get("deleteFeedRecord", {
				params: {
					id: self.deleteFeedId
				}
			}).then(response => {
				if(response.data.successfullyDelete){
					self.tip(response.data.message, "success", 200);
					self.getAllFeed();
					self.deleteFeedDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmDeleteDistrict() {
			var self = this;
			axios.get("deleteDistrict", {
				params: {
					id: self.deleteDistrictId
				}
			}).then(response => {
				if(response.data.successfullyDelete){
					self.tip(response.data.message, "success", 200);
					self.getAllDistrict();
					self.deleteDistrictDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},

		confirmDeleteTherapy() {
			var self = this;
			axios.get("deleteTherapy", {
				params: {
					id: self.deleteTherapyId
				}
			}).then(response => {
				if(response.data.successfullyDelete){
					self.tip(response.data.message, "success", 200);
					self.getAllTherapy();
					self.deleteTherapyDialogFormVisible = false;
				}
				else{
					self.tip(response.data.message, "warning", 200);
				}
			}).catch(error => {
				self.tip("网络错误，请重试", "warning", 200);
			});
		},
		logout() {
			this.logoutDialogVisible = false;
			setCookie("isLogin", true);
		}
	}
});

index_page.getAllAnimal();
index_page.getAllSpecies();
index_page.getAllWorker();
index_page.getAllArea();
index_page.getAllFood();
index_page.getAllFeed();
index_page.getAllStock();
index_page.getAllDistrict();
index_page.getAllTeam();
index_page.getAllTherapy();

// 基于准备好的dom，初始化echarts实例
var animalChart = echarts.init(document.getElementById('animal_chart'));
var foodCostChart = echarts.init(document.getElementById('food_cost_chart'));

// 指定图表的配置项和数据
var animalChartOption = {
    tooltip: {
        trigger: 'item'
    },
    legend: {
        top: '5%',
        left: 'center'
    },
    series: [
        {
            name: '访问来源',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
            },
            label: {
                show: false,
                position: 'center'
            },
            emphasis: {
                label: {
                    show: true,
                    fontSize: '40',
                    fontWeight: 'bold'
                }
            },
            labelLine: {
                show: false
            },
            data: [
            ]
        }
    ]
};

var foodCostChartOption = {
    xAxis: {
        type: 'category',
        data: []
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: [],
        type: 'bar',
        showBackground: true,
        backgroundStyle: {
            color: 'rgba(180, 180, 180, 0.2)'
        }
    }]
};

// 实时更新数据
setInterval(() => {
	animalChartOption.series[0].data = [];
	var animalSpeciesGroup = index_page.animal_species_group;
	for(var i=0;i<animalSpeciesGroup.length;i++){
		animalChartOption.series[0].data.push({
			value: animalSpeciesGroup[i].num,
			name: animalSpeciesGroup[i].name
		});
	}
	animalChart.setOption(animalChartOption);

	foodCostChartOption.xAxis.data = [];
	foodCostChartOption.series[0].data = [];
	var foodSpeciesGroup = index_page.food_species_group;
	for(var i=0;i<foodSpeciesGroup.length;i++){
		foodCostChartOption.xAxis.data.push(foodSpeciesGroup[i].name);
		foodCostChartOption.series[0].data.push(foodSpeciesGroup[i].sum);
	}
	foodCostChart.setOption(foodCostChartOption);

	for(var i=0;i<index_page.foodTableData.length;i++){
		let foodShortChart = echarts.init(document.getElementById(index_page.foodTableData[i].id))
		let foodShortChartOption = {
		    series: [{
		        type: 'gauge',
		        startAngle: 180,
		        endAngle: 0,
		        min: 0,
		        max: 1000,
		        splitNumber: 1,
		        axisLine: {
		            lineStyle: {
		                width: 6,
		                color: [
		                    [0.05, '#FF6E76'],
		                    [1, '#7CFFB2']
		                ]
		            }
		        },
		        pointer: {
		            icon: 'path://M12.8,0.7l12,40.1H0.7L12.8,0.7z',
		            length: '12%',
		            width: 20,
		            offsetCenter: [0, '-60%'],
		            itemStyle: {
		                color: 'auto'
		            }
		        },
		        axisTick: {
		            length: 12,
		            lineStyle: {
		                color: 'auto',
		                width: 2
		            }
		        },
		        splitLine: {
		            length: 20,
		            lineStyle: {
		                color: 'auto',
		                width: 5
		            }
		        },
		        axisLabel: {
		            color: '#464646',
		            fontSize: 20,
		            distance: -60,
		            formatter: function (value) {
		                if (value > 0.05) {
		                    return '库存充足';
		                }
		                else {
		                    return '库存不足';
		                }
		            }
		        },
		        title: {
		            offsetCenter: [0, '-40%'],
		            fontSize: 30
		        },
		        detail: {
		            fontSize: 50,
		            offsetCenter: [0, '0%'],
		            valueAnimation: true,
		            formatter: function (value) {
		                return value;
		            },
		            color: 'auto'
		        },
		        data: [{
		            value: index_page.foodTableData[i].storage,
		            name: index_page.foodTableData[i].name
		        }]
		    }]
		};
		foodShortChart.setOption(foodShortChartOption);
	}
}, 1000);

var intervalId = setInterval(() => {
	if(getCookie("isLogin") !== "ok") {
		setTimeout(() => {
			window.clearInterval(intervalId);
			window.location.href = "login.html";
		}, 200);
	}
}, 200);
