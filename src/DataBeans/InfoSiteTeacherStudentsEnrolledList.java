/*
 * Created on 28/Mai/2003
 *
 * 
 */
package DataBeans;

import java.util.List;

/**
 * @author Jo�o Mota
 *
 */
public class InfoSiteTeacherStudentsEnrolledList implements ISiteComponent {
	private List infoStudents;
	private InfoExam infoExam;
	/**
	 * 
	 */
	public InfoSiteTeacherStudentsEnrolledList() {

	}
	public InfoSiteTeacherStudentsEnrolledList(List infoStudents,InfoExam infoExam) {
		setInfoExam(infoExam);
		setInfoStudents(infoStudents);
	}

	public int getSize() {
		if (getInfoStudents() == null) {
			return 0;
		} else {
			return getInfoStudents().size();
		}
	}

	/**
	 * @return
	 */
	public List getInfoStudents() {
		return infoStudents;
	}

	/**
	 * @param list
	 */
	public void setInfoStudents(List list) {
		infoStudents = list;
	}

	public boolean equals(Object arg0) {
		boolean result = false;

		if (arg0 instanceof InfoSiteTeacherStudentsEnrolledList) {
			InfoSiteTeacherStudentsEnrolledList component =
				(InfoSiteTeacherStudentsEnrolledList) arg0;
			result =
				component.getInfoStudents().containsAll(this.getInfoStudents())
					&& this.getInfoStudents().containsAll(
						component.getInfoStudents());
		}

		return result;
	}

	/**
	 * @return
	 */
	public InfoExam getInfoExam() {
		return infoExam;
	}

	/**
	 * @param exam
	 */
	public void setInfoExam(InfoExam exam) {
		infoExam = exam;
	}

}
