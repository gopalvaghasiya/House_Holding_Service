var sendotp = document.getElementById("sendotp");
var content = document.getElementById("content");


  function sendOTP(){
  var phone = document.getElementById("phone");
					var phoneno = /^\d{10}$/;

					if (phone.value.match(phoneno)) {
						var str = "<div class='row control-group'>"
								+ "<div class='form-group col-xs-5 floating-label-form-group controls'>"
								+ "<label for='phone'>OTP</label>"
								+ "<input type='text' class='form-control' placeholder='Enter OTP' id='otp' required"
								+ "data-validation-required-message='Please enter OTP.'>"
								+ "<p class='help-block text-danger'></p>"
								+ "</div></div><br><div id='success'></div>"
								+ "<div class='row'><div class='form-group col-xs-12'>"
								+ "<button class='btn btn-success btn-lg'  id='sub' onclick='registrationData()'>Submit</button>"
								+ "</div></div>";

						content.innerHTML = ""
						content.insertAdjacentHTML('beforeend', str);
					} else {
						var str = "<div class='row control-group'>"
							+ "<div class='form-group col-xs-8 floating-label-form-group controls'>"
							+ "<label for='phone'>Phone Number</label>"
							+ "<input type='tel' class='form-control' placeholder='Phone Number' required"
							+ "<p class='help-block text-danger'>Please Enter Correct No</p>"
							+ "</div>"
							+ "&nbsp <button class='btn btn-success' onClick='sendOTP()' >Send OTP</button>"
							+ "</div>";

					content.innerHTML = ""
					content.insertAdjacentHTML('beforeend', str);
					}
 
  }/*
sendotp
		.addEventListener(
				"click",
				function() {
					var phone = document.getElementById("phone");
					var phoneno = /^\d{10}$/;

					if (phone.value.match(phoneno)) {
						var str = "<div class='row control-group'>"
								+ "<div class='form-group col-xs-5 floating-label-form-group controls'>"
								+ "<label for='phone'>OTP</label>"
								+ "<input type='text' class='form-control' placeholder='Enter OTP' id='otp' required"
								+ "data-validation-required-message='Please enter OTP.'>"
								+ "<p class='help-block text-danger'></p>"
								+ "</div></div><br><div id='success'></div>"
								+ "<div class='row'><div class='form-group col-xs-12'>"
								+ "<button class='btn btn-success btn-lg'  id='sub' onclick='registrationData()'>Submit</button>"
								+ "</div></div>";

						content.innerHTML = ""
						content.insertAdjacentHTML('beforeend', str);
					} else {
						var str = "<div class='row control-group'>"
							+ "<div class='form-group col-xs-8 floating-label-form-group controls'>"
							+ "<label for='phone'>Phone Number</label>"
							+ "<input type='tel' class='form-control' placeholder='Phone Number' required"
							+ "<p class='help-block text-danger'>Please Enter Correct No</p>"
							+ "</div>"
							+ "&nbsp <button class='btn btn-success' id='sendotp' >Send OTP</button>"
							+ "</div>";

					content.innerHTML = ""
					content.insertAdjacentHTML('beforeend', str);
					}

				});
*/
function registrationData() {

	content.innerHTML = "";
	// for name
	var str = "<div class='row control-group'>"
			+ "<div class='form-group col-xs-5 floating-label-form-group controls'>"
			+ "<label for='name'>Name</label>"
			+ "<input type='text' class='form-control' placeholder='Name' id='name' required>"
			+ "<p class='help-block text-danger'></p>" + "</div></div>";

	// for email
	str += "<div class='row control-group'>"
			+ "<div class='form-group col-xs-5 floating-label-form-group controls'>"
			+ "<label for='email'>Email Address</label>"
			+ "<input type='email' class='form-control' placeholder='Email' id='email' required>"
			+ "<p class='help-block text-danger'></p>" + "</div></div>"

	// password
	str += "<div class='row control-group'>"
			+ "<div class='form-group col-xs-5 floating-label-form-group controls'>"
			+ "<label >Password</label>"
			+ "<input type='password' class='form-control' placeholder='Password' id='password' required"
			+ ">" + "<p class='help-block text-danger'></p>" + "</div></div>"

	// re enter password
	str += "<div class='row control-group'>"
			+ "<div class='form-group col-xs-5 floating-label-form-group controls'>"
			+ "<label >Re Enter Password</label>"
			+ "<input type='password' class='form-control' placeholder='Re Enter Password' id='password2' required>"
			+ "<p class='help-block text-danger'></p>" + "</div></div>"

	// city
	str += "<div class='row control-group' id='cityContainer'>"
			+ "<div class='form-group col-xs-5 floating-label-form-group controls'>"
			+ "<label >City</label>"
			+ "<select class='form-control' name='cityId' id='cityId' onchange='selectArea()' required>"
			+ "<option selected disabled value=''>-------Select City------</option>"
			+ "</select>" + "<p class='help-block text-danger'></p>"
			+ "</div></div>"

	// area
	str += "<div class='row control-group' id='areaContainer'>"
			+ "<div class='form-group col-xs-5 floating-label-form-group controls'>"
			+ "<label >Area</label>"
			+ "<select class='form-control' name='areaId' id='areaId'  required>"
			+ "<option selected disabled value=''>-------Select Area------</option>"
			+ "</select>" + "<p class='help-block text-danger'></p>"
			+ "</div></div>"

	// Address
	str += "<div class='row control-group'>"
			+ "<div class='form-group col-xs-5 floating-label-form-group controls'>"
			+ "<label >Address</label>"
			+ "<textarea rows='5' class='form-control' placeholder='Address'"
			+ "id='address' required></textarea>"
			+ "<p class='help-block text-danger'></p>" + "</div></div>"

	// submit
	str += "<br>" + "<div class='row'><div class='form-group col-xs-12'>"
			+ "<input type='submit' class='btn btn-success' >" + "</div></div>";

	content.insertAdjacentHTML('beforeend', str);

	// seting cities

	var cities = new XMLHttpRequest();

	cities.open('GET', 'http://localhost:8080/rest_select_all_city');

	cities.onload = function() {
		var data = JSON.parse(cities.responseText);

		setCities(data);
	};
	cities.send();
};

function setCities(data) {
	var str = "";
	str += "<div class='row control-group'>"
			+ "<div class='form-group col-xs-5 floating-label-form-group controls'>"
			+ "<label >City</label>"
			+ "<select class='form-control' name='cityId' id='cityId' onchange='selectArea()' required>"
			+ "<option selected disabled value=''>-------Select City------</option>"

	for (i = 0; i < data.length; i++) {
		str += "<option value=" + data[i].cityId + ">" + data[i].cityName
				+ "</option>";
	}

	str += "</select>" + "<p class='help-block text-danger'></p>"
			+ "</div></div>"

	document.getElementById("cityContainer").innerHTML = "";
	document.getElementById("cityContainer").insertAdjacentHTML('beforeend',
			str);
}

// select area
function selectArea() {

	var cityId = document.getElementById("cityId").value;

	var areas = new XMLHttpRequest();

	areas.open('GET', 'http://localhost:8080/rest_select_area_by_city_id/'
			+ cityId);

	areas.onload = function() {
		var data = JSON.parse(areas.responseText);

		setAreas(data);
	};
	areas.send();
}

// set area to div
function setAreas(data) {
	var str = "";
	str += "<div class='row control-group'>"
			+ "<div class='form-group col-xs-5 floating-label-form-group controls'>"
			+ "<label >Area</label>"
			+ "<select class='form-control' name='areaId' id='areaId' required>"
			+ "<option selected disabled value=''>-------Select Area------</option>"

	for (i = 0; i < data.length; i++) {
		str += "<option value=" + data[i].areaId + ">" + data[i].areaName
				+ "</option>";
	}

	str += "</select>" + "<p class='help-block text-danger'></p>"
			+ "</div></div>"

	document.getElementById("areaContainer").innerHTML = "";
	document.getElementById("areaContainer").insertAdjacentHTML('beforeend',
			str);
}