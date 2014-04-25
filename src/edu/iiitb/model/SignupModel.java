package edu.iiitb.model;

public class SignupModel {

		private String pincode;
		
		String firstName,lastName,role,address1,address2,city,country,email;
		private String gender;
		String date,password;
		String phonenumber;

		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}
		public void setPassword(String password) {
			this.password=password;
		}

		/**
		 * @return the password
		 */
		
		/**
		 * @param date the date to set
		 */
		public void setDate(String date) {
			this.date = date;
		}
		
		/**
		 * @return the date
		 */
		public String getDate() {
			return date;
		}
		/**
		 * @return the phonenumber
		 */
		public String getPhonenumber() {
			return phonenumber;
		}
		/**
		 * @param phonenumber the phonenumber to set
		 */
		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}
		
		
		/**
		 * @return the firstName
		 */
		public String getFirstName() {
			return firstName;
		}
		/**
		 * @param firstName the firstName to set
		 */
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		/**
		 * @return the lastName
		 */
		public String getLastName() {
			return lastName;
		}
		/**
		 * @param lastName the lastName to set
		 */
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		/**
		 * @return the address1
		 */
		public String getAddress1() {
			return address1;
		}
		/**
		 * @param address1 the address1 to set
		 */
		public void setAddress1(String address1) {
			this.address1 = address1;
		}
		/**
		 * @return the address2
		 */
		public String getAddress2() {
			return address2;
		}
		/**
		 * @param address2 the address2 to set
		 */
		public void setAddress2(String address2) {
			this.address2 = address2;
		}
		/**
		 * @return the city
		 */
		public String getCity() {
			return city;
		}
		/**
		 * @param city the city to set
		 */
		public void setCity(String city) {
			this.city = city;
		}
		/**
		 * @return the country
		 */
		public String getCountry() {
			return country;
		}
		/**
		 * @param country the country to set
		 */
		public void setCountry(String country) {
			this.country = country;
		}
		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}
		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		/**
		 * @return the sellerDescription
		 */
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}

		
		
	}

