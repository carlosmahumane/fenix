insert into CONTENT (OJB_CONCRETE_CLASS,NAME,DESCRIPTION,CREATION_DATE,KEY_FORUM_EXECUTION_COURSE,OLD_ID_INTERNAL) select OJB_CONCRETE_CLASS,NAME,DESCRIPTION,CREATION_DATE_DATE_TIME,KEY_EXECUTION_COURSE,ID_INTERNAL FROM FORUM;

insert into CONTENT (OJB_CONCRETE_CLASS,CREATION_DATE,BODY,KEY_CREATOR, OLD_ID_INTERNAL) select 'net.sourceforge.fenixedu.domain.messaging.ConversationMessage',CREATION_DATE_DATE_TIME,BODY,KEY_CREATOR,ID_INTERNAL FROM CONVERSATION_MESSAGE;

insert into CONTENT (OJB_CONCRETE_CLASS,CREATION_DATE,TITLE,KEY_CREATOR,OLD_ID_INTERNAL) select 'net.sourceforge.fenixedu.domain.messaging.ConversationThread',CREATION_DATE_DATE_TIME,SUBJECT,KEY_CREATOR,ID_INTERNAL FROM CONVERSATION_THREAD;

update FORUM_SUBSCRIPTION FS, CONTENT C set FS.KEY_FORUM=C.ID_INTERNAL where C.OLD_ID_INTERNAL=FS.KEY_FORUM AND C.OJB_CONCRETE_CLASS='net.sourceforge.fenixedu.domain.messaging.ExecutionCourseForum';

insert into NODE(OJB_CONCRETE_CLASS,KEY_PARENT, KEY_CHILD, VISIBLE,ASCENDING) select 'net.sourceforge.fenixedu.domain.contents.DateOrderedNode', C1.ID_INTERNAL,C2.ID_INTERNAL,'1','0' FROM CONTENT C1, CONTENT C2, CONVERSATION_THREAD CT WHERE C1.OJB_CONCRETE_CLASS='net.sourceforge.fenixedu.domain.messaging.ExecutionCourseForum' AND C2.OJB_CONCRETE_CLASS='net.sourceforge.fenixedu.domain.messaging.ConversationThread' AND C2.OLD_ID_INTERNAL=CT.ID_INTERNAL AND C1.OLD_ID_INTERNAL=CT.KEY_FORUM ;

insert into NODE(OJB_CONCRETE_CLASS,KEY_PARENT, KEY_CHILD, VISIBLE,ASCENDING) select 'net.sourceforge.fenixedu.domain.contents.DateOrderedNode', C1.ID_INTERNAL,C2.ID_INTERNAL,'1','1' FROM CONTENT C1, CONTENT C2, CONVERSATION_MESSAGE CT WHERE C1.OJB_CONCRETE_CLASS='net.sourceforge.fenixedu.domain.messaging.ConversationThread' AND C2.OJB_CONCRETE_CLASS='net.sourceforge.fenixedu.domain.messaging.ConversationMessage' AND C2.OLD_ID_INTERNAL=CT.ID_INTERNAL AND C1.OLD_ID_INTERNAL=CT.KEY_CONVERSATION_THREAD;

