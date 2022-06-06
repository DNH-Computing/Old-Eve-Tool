package nz.net.dnh.evetool.hibernate.eve;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="invTypes", catalog="EVE")
public class Item implements Serializable, nz.net.dnh.evetool.entities.eve.Item {
	private int typeID;
	private String name;
	private double mass;
	private double volume;
	
	@Override
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="typeID", unique=true, nullable=false)
	public int getTypeID() {
		return this.typeID;
	}
	
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	@Override
	@Column(name="typeName")
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	@Column(name="mass")
	public double getMass() {
		return this.mass;
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}

	@Override
	@Column(name="volume")
	public double getVolume() {
		return this.volume;
	}
	
	public void setVolume(double volume) {
		this.volume = volume;
	}

}
