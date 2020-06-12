package DBase_Class;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name="getListAllTableColumn", query="SELECT e FROM TableColumn e ORDER BY e.tipe_Table ASC")
@NamedQuery(name="getListTableColumnByTipe_Table", query="SELECT e FROM TableColumn e WHERE e.tipe_Table = :text")

public class TableColumn  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_TableColumn;
	private String tipe_Table;
	private String name_Column;
	private String Class_Column;
	private String tipe_Column;
	private String keyMap;
	private Boolean inVisible;
	
	public TableColumn (String tipe_Table, String name_Column, String Class_Column,String tipe_Column, String keyMap, Boolean inVisible){
		super();
		this.setTipe_Table(tipe_Table);
		this.setName_Column(name_Column);
		this.setClass_Column(Class_Column);
		this.setTipe_Column(tipe_Column);
		this.setKeyMap(keyMap);
		this.setInVisible(inVisible);
	}
	
	public TableColumn (){
		super();
	}

	public int getId_TableColumn() {
		return id_TableColumn;
	}
	
	public String getName_Column() {
		return name_Column;
	}

	public void setName_Column(String name_Column) {
		this.name_Column = name_Column;
	}

	public String getClass_Column() {
		return Class_Column;
	}

	public void setClass_Column(String class_Column) {
		Class_Column = class_Column;
	}

	public String getTipe_Column() {
		return tipe_Column;
	}

	public void setTipe_Column(String tipe_Column) {
		this.tipe_Column = tipe_Column;
	}

	public String getTipe_Table() {
		return tipe_Table;
	}

	public void setTipe_Table(String tipe_Table) {
		this.tipe_Table = tipe_Table;
	}

	public Boolean getInVisible() {
		return inVisible;
	}

	public void setInVisible(Boolean inVisible) {
		this.inVisible = inVisible;
	}

	public String getKeyMap() {
		return keyMap;
	}

	public void setKeyMap(String keyMap) {
		this.keyMap = keyMap;
	}
}
