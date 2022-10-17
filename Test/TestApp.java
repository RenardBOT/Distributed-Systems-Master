package sd.akka;

import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import sd.akka.actor.*;

public class TestApp {
	public static void main(String[] args) {

		ActorSystem actorSystem = ActorSystem.create();
	/* 	ActorRef helloActor = actorSystem.actorOf(HelloWorldActor.props());
		// Envoi de messages simples
        helloActor.tell(new HelloWorldActor.SayHello("Akka"), ActorRef.noSender());
        helloActor.tell(new HelloWorldActor.SayBye(), ActorRef.noSender());*/
        
        // Jeu de ping pong (communication entre acteurs, et attente d'une réponse)
        /* ActorRef arbitreActor = actorSystem.actorOf(ArbitreActor.props(), "arbitre");
        arbitreActor.tell(new ArbitreActor.GetScore(), ActorRef.noSender());
        CompletionStage<Object> result = Patterns.ask(arbitreActor, new ArbitreActor.StartGame(), Duration.ofSeconds(10));
        try {
			result.toCompletableFuture().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
        arbitreActor.tell(new ArbitreActor.GetScore(), ActorRef.noSender());
        
        // Acteur jour/nuit (changement de comportement)
        ActorRef jourNuitActor = actorSystem.actorOf(JourNuitActor.props());
        for (int i = 0; i < 24; i++) {
        	jourNuitActor.tell(new JourNuitActor.Increment(), ActorRef.noSender());
        }
        
        // Calcul de nombres premiers (routage des messages)
        // On démarre un pool de 5 acteurs du même type, et on route les messages avec une stratégie round robin
        ActorRef router = actorSystem.actorOf(new RoundRobinPool(5).props(NombrePremierActor.props()));
        for (int i = 3; i < 10; i++) {
        	router.tell(new NombrePremierActor.Nombre(i), ActorRef.noSender());
        } */ 


        // ------------ EXERCICE 1

        
        /*ActorRef telephone = actorSystem.actorOf(TelephoneActor.props(10));

        CompletionStage<Object> result = Patterns.ask(telephone, new TelephoneActor.sendString("plop"), Duration.ofSeconds(10));
        try {
			String res = (String) result.toCompletableFuture().get();

            System.out.println(res);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
        
        telephone.tell(new TelephoneActor.sendString("plop"), ActorRef.noSender()); */

        
        // ------------ EXERCICE 2
        /* ActorRef stockCrayon = actorSystem.actorOf(Stock.props("crayon"));
        stockCrayon.tell(new Stock.Print(),ActorRef.noSender());
        stockCrayon.tell(new Stock.Deliver(18500),ActorRef.noSender());
        stockCrayon.tell(new Stock.Print(),ActorRef.noSender());
        stockCrayon.tell(new Stock.Acquire(15000),ActorRef.noSender());
        stockCrayon.tell(new Stock.Print(),ActorRef.noSender());
        stockCrayon.tell(new Stock.Acquire(7000),ActorRef.noSender());
        stockCrayon.tell(new Stock.Print(),ActorRef.noSender());
        stockCrayon.tell(new Stock.Acquire(10),ActorRef.noSender());
        stockCrayon.tell(new Stock.Print(),ActorRef.noSender());
        stockCrayon.tell(new Stock.Deliver(5000),ActorRef.noSender());
        stockCrayon.tell(new Stock.Print(),ActorRef.noSender());
        stockCrayon.tell(new Stock.Acquire(2000),ActorRef.noSender());
        stockCrayon.tell(new Stock.Print(),ActorRef.noSender()); */

        // ------------ EXERCICE 3
        



        
        // Arrêt du système d'acteurs
        actorSystem.terminate();
	}
}
