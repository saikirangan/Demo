import com.ibm.dbb.build.*
//** create PDS **//
new CreatePDS().dataset("ADCDS.IDZ.COBOL").options("cyl space(1,1) lrecl(80) dsorg(PO) recfm(F,B) dsntype(library) msg(1)").execute()
new CreatePDS().dataset("ADCDS.IDZ.OBJ").options("cyl space(1,1) lrecl(80) dsorg(PO) recfm(F,B) dsntype(library) msg(1)").execute()
//** copy zFS directory to a PDS **//
def file = new File("/u/ibmuser/hello.idzcbl/hello.idzcbl")
def copy = new CopyToPDS()
copy.setFile(file)
copy.setDataset("ADCDS.IDZ.COBOL")
copy.setMember("HELLO")
copy.execute()
new CopyToPDS().file(new File("/u/ibmuser/hello.idzcbl/hello.idzcbl")).dataset("ADCDS.IDZ.COBOL").member("HELLO").execute()
//** cmpl the Program **//
def cmpl = new MVSExec().pgm("IGYCRCTL").parm("LIB")
cmpl.dd(new DDStatement().name("SYSIN").dsn("ADCDS.IDZ.COBOL(HELLO)").options("shr"))
cmpl.dd(new DDStatement().name("SYSLIN").dsn("ADCDS.IDZ.OBJ(HELLO)").options("shr"))
cmpl.dd(new DDStatement().name("SYSUT1").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT2").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT3").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT4").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT5").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT6").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT7").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT8").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT9").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT10").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT11").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT12").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT13").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT14").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT15").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT16").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSUT17").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("SYSMDECK").options("cyl space(5,5) unit(vio) new"))
cmpl.dd(new DDStatement().name("TASKLIB").dsn("IGY630.SIGYCOMP").options("shr"))
cmpl.dd(new DDStatement().name("SYSPRINT").options("cyl space(5,5) unit(vio) new"))
cmpl.copy(new CopyToHFS().ddName("SYSPRINT").file(new File("/u/ibmuser/hello.idzcbl/hello.log")))
def rc = cmpl.execute()

if (rc > 4)
    println("cmpl failed!     RC=$rc")
else
    println("cmpl successful!     RC=$rc")
