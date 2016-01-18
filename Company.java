package WebDriverDemo.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Company {	
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	
		private  int id;
		private  String companyName;
		private  String companyTicker;
		private  String companyData;
		
		public Company (int id, String companyName, String companyTicker, String companyData) {						
			super();
			this.id = id;
			this.companyName = companyName; 
			this.companyTicker = companyTicker;
			this.companyData = companyData;
		}
		
		public Company () {
			super();
		}
		
		public String getCompanyName() {
			return companyName;
		}

		public String getCompanyTicker() {
			return companyTicker;
		}

		public String getCompanyData() {
			return companyData;
		}
			
		public void setId(int id) {
			this.id = id;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public void setCompanyTicker(String companyTicker) {
			this.companyTicker = companyTicker;
		}

		public void setCompanyData(String companyData) {
			this.companyData = companyData;
		}
		
		@Override
		public String toString () {
			return "companyName" + " " + "companyTicker" + " " + "companyData"; 
		}
}
