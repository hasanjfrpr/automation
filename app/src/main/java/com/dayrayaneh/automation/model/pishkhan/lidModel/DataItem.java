package com.dayrayaneh.automation.model.pishkhan.lidModel;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Demo")
	private Object demo;

	@SerializedName("PeygiriTelefoni")
	private Object peygiriTelefoni;

	@SerializedName("EraeePishFaktor")
	private Object eraeePishFaktor;

	@SerializedName("Fld_ProformaKind_Name")
	private Object fldProformaKindName;

	@SerializedName("PeygiriNashode")
	private String peygiriNashode;

	@SerializedName("DarEntezarMoshtari")
	private Object darEntezarMoshtari;

	@SerializedName("MonjarBeForosh")
	private Object monjarBeForosh;

	@SerializedName("MonsarefAzKharid")
	private Object monsarefAzKharid;

	@SerializedName("ErjaBeNamayande")
	private Object erjaBeNamayande;

	@SerializedName("TedadKol")
	private String tedadKol;

	@SerializedName("KharidAzSherkatDigar")
	private Object kharidAzSherkatDigar;

	@SerializedName("AdamMojodi")
	private Object adamMojodi;

	@SerializedName("MoshtariGheireMortabet")
	private Object moshtariGheireMortabet;

	@SerializedName("Perezent")
	private Object perezent;

	public Object getDemo(){
		return demo;
	}

	public Object getPeygiriTelefoni(){
		return peygiriTelefoni;
	}

	public Object getEraeePishFaktor(){
		return eraeePishFaktor;
	}

	public Object getFldProformaKindName(){
		return fldProformaKindName;
	}

	public String getPeygiriNashode(){
		return peygiriNashode;
	}

	public Object getDarEntezarMoshtari(){
		return darEntezarMoshtari;
	}

	public Object getMonjarBeForosh(){
		return monjarBeForosh;
	}

	public Object getMonsarefAzKharid(){
		return monsarefAzKharid;
	}

	public Object getErjaBeNamayande(){
		return erjaBeNamayande;
	}

	public String getTedadKol(){
		return tedadKol;
	}

	public Object getKharidAzSherkatDigar(){
		return kharidAzSherkatDigar;
	}

	public Object getAdamMojodi(){
		return adamMojodi;
	}

	public Object getMoshtariGheireMortabet(){
		return moshtariGheireMortabet;
	}

	public Object getPerezent(){
		return perezent;
	}
}