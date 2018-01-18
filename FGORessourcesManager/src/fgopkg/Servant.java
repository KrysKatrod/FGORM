package fgopkg;

import java.io.Serializable;

public class Servant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
	private String Star;
	private String name;
	private String cls;
	private String skill1[][][]=new String[9][5][5];
	private String ascension[][][]=new String[4][5][5];
	private int lvl;
	private int ascrank;
	private int s1lvl;
	private int s2lvl;
	private int s3lvl;
	private int np;
	
	public Servant() {
		ID="";
		Star="";
		name="";
		cls="";
		for(int i=0;i<9;i++) {
			for(int k=0;k<5;k++) {
				for (int j=0;j<5;j++) {
					skill1[i][k][j]="";
					if(i<=3) ascension[i][k][j]="";
				}
			}
		}
		lvl=0;
		ascrank=0;
		s1lvl=1;
		s2lvl=1;
		s3lvl=1;
		np=1;
	}
	public Servant(String id,String star,String nawa,String cls,String s1to2,String s2to3,String s3to4,String s4to5,String s5to6,String s6to7,String s7to8,String s8to9,String s9to10,String asc1,String asc2,String asc3,String asc4) {
		this.ID=id;
		this.Star=star;
		this.name=nawa;
		this.cls=cls;
		for(int k=0;k<5;k++) {
			for (int j=0;j<5;j++) {
				String temp[]=s1to2.split("µ");
				/*for(int i=0;i<temp.length;i++) {
					System.out.println(temp[i]);
				}*/
				String mat[][]=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						skill1[0][i][ii]=mat[i][ii];
					}
				}
				temp=s2to3.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						skill1[1][i][ii]=mat[i][ii];
					}
				}
				temp=s3to4.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						skill1[2][i][ii]=mat[i][ii];
					}
				}
				temp=s4to5.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						skill1[3][i][ii]=mat[i][ii];
					}
				}
				temp=s5to6.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						skill1[4][i][ii]=mat[i][ii];
					}
				}
				temp=s6to7.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						skill1[5][i][ii]=mat[i][ii];
					}
				}
				temp=s7to8.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						skill1[6][i][ii]=mat[i][ii];
					}
				}
				temp=s8to9.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						skill1[7][i][ii]=mat[i][ii];
					}
				}
				temp=s9to10.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						skill1[8][i][ii]=mat[i][ii];
					}
				}
				temp=asc1.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						ascension[0][i][ii]=mat[i][ii];
					}
				}
				temp=asc2.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						ascension[1][i][ii]=mat[i][ii];
					}
				}
				temp=asc3.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						ascension[2][i][ii]=mat[i][ii];
					}
				}
				temp=asc4.split("µ");
				mat=new String[temp.length][];
				for(int i=0;i<temp.length;i++) {
					mat[i]=temp[i].split("_");
				}
				for(int i=0;i<temp.length;i++) {
					for(int ii=0;ii<mat[i].length;ii++) {
						ascension[3][i][ii]=mat[i][ii];
					}
				}
			}
		}
		lvl=0;
		ascrank=0;
		s1lvl=1;
		s2lvl=1;
		s3lvl=1;
		np=1;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getStar() {
		return Star;
	}
	public void setStar(String star) {
		Star = star;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public String[][][] getSkill1() {
		return skill1;
	}
	public String getSkill1(int lvl,int type,int quantity) {
		return skill1[lvl][type][quantity];
	}
	public void setSkill1(String[][][] skill1) {
		this.skill1 = skill1;
	}
	public String[][][] getAscension() {
		return ascension;
	}
	public void setAscension(String[][][] ascension) {
		this.ascension = ascension;
	}
	public String getAscension(int i,int j,int k) {
		return ascension[i][j][k];
	}
	public int getAscrank() {
		return ascrank;
	}
	public void setAscrank(int ascrank) {
		this.ascrank = ascrank;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public int getS1lvl() {
		return s1lvl;
	}
	public void setS1lvl(int s1lvl) {
		this.s1lvl = s1lvl;
	}
	public int getS2lvl() {
		return s2lvl;
	}
	public void setS2lvl(int s2lvl) {
		this.s2lvl = s2lvl;
	}
	public int getS3lvl() {
		return s3lvl;
	}
	public void setS3lvl(int s3lvl) {
		this.s3lvl = s3lvl;
	}
	public int getNp() {
		return np;
	}
	public void setNp(int np) {
		this.np = np;
	}
	@Override
	public String toString() {
		String disp;
		disp = "Servant [ID=" + ID + ", Star=" + Star + ", name=" + name + ", cls=" + cls+"]\n\nSkill materials needed :\n";
		for(int i=0;i<9;i++) {
			disp+="Level "+(i+1)+" à "+(i+2)+" : ";
			for(int ii=0;ii<skill1[i].length;ii++) {
				for(int iii=0;iii<skill1[i][ii].length;iii++) {
					if(skill1[i][ii][iii]!=null) disp+=skill1[i][ii][iii]+" ";
				}
			}
			disp+="\n";
		}
		disp+="\nAscension material :\n\n";
		for(int i=0;i<4;i++) {
			disp+="Ascension "+(i+1)+" : ";
			for(int ii=0;ii<skill1[i].length;ii++) {
				for(int iii=0;iii<skill1[i][ii].length;iii++) {
					if(ascension[i][ii][iii]!=null) disp+=ascension[i][ii][iii]+" ";
				}
			}
			disp+="\n";
		}
		return disp;
				
	}
	
	
	
	
}
