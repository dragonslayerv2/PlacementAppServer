package in.ac.nitkkr.server.database;

public class Tables {

    public static class Students {
        public final static String TABLE_NAME="STUDENTS";
        public static final String EMAIL = "email";
        public final static String ID = "id";
        public final static String NAME = "name";
        public final static String PASSWORD = "password";
        public final static String RESUME_LINK = "resume_link";
        public final static String COURSE = "course";
        public final static String MAJORS = "majors";
        public final static String BACKLOGS = "backlogs";
        public final static String CGPA = "cgpa";
    }
    
    public static class Notification{
        public final static String TABLE_NAME ="NOTIFICATION";
        public final static String ID = "ID";
        public final static String JOB_ID = "JOB_ID";
        public final static String COMPANY_ID = "COMPANY_ID";
        public final static String TITLE = "TITLE";
        public final static String DESCRIPTION = "DESCRIPTION";
    }
    
    public static class Company{
        public final static String TABLE_NAME = "COMPANY";
        public final static String ID = "ID";
        public final static String NAME = "NAME";
        public final static String DESCRIPTION = "DESCRIPTION";
        public final static String LOGO_URL = "LOGO_URL";
        public final static String URL = "URL";
    }
    
    public static class ELIGIBLE{
        public final static String TABLE_NAME = "ELIGIBLE";
        public final static String STUDENT_ID ="STUDENT_ID";
        public final static String JOB_ID ="JOB_ID";
        public final static String APPLIED ="APPLIED";
        public final static String STATUS ="STATUS";
    }
    
    public static class JOB{
        public final static String TABLE_NAME="JOB";
        public final static String ID="ID";
        public final static String COMPANY_ID="COMPANY_ID";
        public final static String PROFILE="PROFILE";
        public final static String ELIGIBILITY_CRITERIA="ELIGIBILITY_CRITERIA";
        public final static String LOCATION="LOCATION";
        public final static String PACKAGE="PACKAGE";
        public final static String DESCRIPTION="DESCRIPTION";
        public final static String POSTED_DATE="POSTED_DATE";
        public final static String PACKAGE_STATUS="PACKAGE_STATUS";
    }
    
    public static class NOTIFICATION_STUDENT_RELATIONSHIP{
        public final static String TABLE_NAME="NOTIFICATION_STUDENT_RELATIONSHIP";
        public final static String STUDENT_ID="STUDENT_ID";
        public final static String NOTIFICATION_ID="NOTIFICATION_ID";
    }
    
    public static class AUTHORITIES{
        public final static String TABLE_NAME = "AUTHORITIES";
        public final static String ID="STUDENT_ID";
        public final static String NAME="NOTIFICATION_ID";
        public final static String DESIGNATION="DESIGNATION";
        public final static String PASSWORD="PASSWORD";
    }
}
