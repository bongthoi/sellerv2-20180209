package seller.domain;

import org.springframework.data.domain.Persistable;

public class Role implements Persistable<String>{
	
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -1805113856902059423L;
	private transient boolean persisted;
	
		private String id;
		private String name;
		
		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return id;
		}
		@Override
		public boolean isNew() {
			// TODO Auto-generated method stub
			return !persisted;
		}
		public void setPersisted(boolean persisted) {
			this.persisted = persisted;
		}
		
		public boolean isPersisted() {
			return persisted;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Role(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public Role() {
			super();
			// TODO Auto-generated constructor stub
		}	
		
		
}
