package sd.akka.actor;
import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.ActorRef;

public class TelephoneActor extends AbstractActor {

    private ActorRef next;

    private TelephoneActor(int n) {
        if(n-1 == 0){
            next = null;
            System.out.println("Rank 0 crée");
        }
        else{
            next = getContext().actorOf(TelephoneActor.props(n-1));
            System.out.println("Rank "+(n-1)+ " crée");
        }
	}

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(sendString.class, message -> sendString(message.content))
                .match(sendLastString.class, message -> getLastString(message.content))
                .build();
    }

    public void sendString(String content){
        String newContent = content+"@";
        
        if(next == null){
            System.out.println("-- Dernier fils à reçu le message --");
            getSender().tell(newContent,getSelf());
            
        }else{    
            System.out.println("-- Fils à reçu le message --");
            next.forward(new TelephoneActor.sendString(newContent), getContext());
        }
    }

    public void getLastString(String content){
        System.out.println("Reçu le string : "+content);
    }

    public static Props props(int n) {
		return Props.create(TelephoneActor.class,n);
	}

    public interface Message {}

    public static class sendString implements Message {
        public final String content;
		public sendString(String content) {
            this.content = content;
        }
	}

    public static class sendLastString implements Message {
        public final String content;
		public sendLastString(String content) {
            this.content = content;
        }
	}



    
}
