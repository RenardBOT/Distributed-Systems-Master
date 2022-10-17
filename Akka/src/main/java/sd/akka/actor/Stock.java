package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.ActorRef;

public class Stock extends AbstractActor {
    private String product;
    private int qty;
    
    private Stock(String product){
        this.product = product;
        this.qty = 0;
    }

    @Override
	public Receive createReceive() {
		return receiveBuilder()
                .match(Deliver.class,message->increaseStock(message.qty))
                .match(Acquire.class,message->decreaseStock(message.qty))
                .match(Print.class,message->printDebug())
				.build();
	}

    public Receive createReceiveShortage() {
		return receiveBuilder()
                .match(Deliver.class,message->increaseStock(message.qty))
                .match(Acquire.class,message->noStock())
                .match(Print.class,message->printDebug())
				.build();
	}

    public void increaseStock(int qty){
        if(qty <= 0){
            System.out.println("ERREUR - Valeur nulle ou négative");
            return;
        }
        if(this.qty == 0){
            getContext().become(createReceive());
        }
        this.qty+=qty;
    }

    public void decreaseStock(int qty){
        if(qty <= 0){
            System.out.println("ERREUR - Valeur nulle ou négative");
            return;
        }
        if(this.qty < qty){
            System.out.println("Demande : "+qty+" | Livraison partielle : "+this.qty+" "+this.product+"(s)");
            this.qty = 0;
            getContext().become(createReceiveShortage());
        }else{
            System.out.println("Demande : "+qty+" | Livraison complète : "+qty+" "+this.product+"(s)");
            this.qty -= qty;
        }

    }

    public void noStock(){
        System.out.println("Rupture de stock :(");
    }

    public void printDebug(){
        System.out.println("Produit : " + this.product + " | Quantité : " + this.qty);
    }


    public static Props props(String product) {
		return Props.create(Stock.class,product);
	}

    public interface Message {}

    public static class Deliver implements Message {
        public final int qty;
		public Deliver(int qty) {
            this.qty = qty;
        }
	}
	
	public static class Acquire implements Message {
        public final int qty;
		public Acquire(int qty) {
            this.qty = qty;
        }
	}

    public static class Print implements Message {
		public Print() {
        }
	}
}
