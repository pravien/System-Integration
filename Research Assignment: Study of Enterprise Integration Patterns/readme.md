# Event Message

* Visual representation (diagram)
  
    ![](https://www.enterpriseintegrationpatterns.com/img/EventMessageSolution.gif)

* Short definition

    - Used to notify obervers  when a event happens.

* Problem it can solve
  
  - It can solve problems where you you have oberservers who needs to be notified.

* Solution it provides
  
   - It helps one notify all consumers.

* What is it good for
  
  - It is very good for messageing application or applications where an oberserver need act when an event happens. In general it is good when you want to broadcast events.

* What is it not so good for
    - It is not good if you only want to notify one specific consumer. 
  
* One example of implementation

    - One example for a problem you can use this pattern is Stock trading. Where you have many system there need to receive information when a trade has been made.