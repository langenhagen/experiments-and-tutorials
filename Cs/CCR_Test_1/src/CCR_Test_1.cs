using System;
using System.Collections.Generic;
using System.ComponentModel;
using Microsoft.Ccr.Core;
using Microsoft.Dss.Core.Attributes;
using Microsoft.Dss.ServiceModel.Dssp;
using Microsoft.Dss.ServiceModel.DsspServiceBase;
using W3C.Soap;
using submgr = Microsoft.Dss.Services.SubscriptionManager;

namespace CCR_Test_1
{
    [Contract(Contract.Identifier)]
    [DisplayName("CCR_Test_1")]
    [Description("CCR_Test_1 service (no description provided)")]
    class CCR_Test_1Service : DsspServiceBase
    {

        // INSTANCE VARS //////////////////////////////////////////////////////////////////////////
        double[] globalDblArray = { 99, 2, 3, 4 };
        Port<double> globalDblPort;

        /// <summary>
        /// Service state
        /// </summary>
        [ServiceState]
        CCR_Test_1State _state = new CCR_Test_1State();

        /// <summary>
        /// Main service port
        /// </summary>
        [ServicePort("/CCR_Test_1", AllowMultipleInstances = true)]
        CCR_Test_1Operations _mainPort = new CCR_Test_1Operations();

        [SubscriptionManagerPartner]
        submgr.SubscriptionManagerPort _submgrPort = new submgr.SubscriptionManagerPort();

        /// <summary>
        /// Service constructor
        /// </summary>
        public CCR_Test_1Service(DsspServiceCreationPort creationPort)
            : base(creationPort)
        {

            globalDblPort = new Port<double>();

            for (byte i=0; i < 4; i++ )
                globalDblPort.Post(globalDblArray[i]);
        }

        /// <summary>
        /// Service start
        /// </summary>
        protected override void Start()
        {
            base.Start();

            System.Threading.Thread.Sleep(1000);    // just for convenience
            // ES GEHT LOOHOOOS ///////////////////////////////////////////////////////////////////
            Console.WriteLine("\n\nHallo!... es geht looos!\n");


            // ***add tests here***
            testArbtiter_Choice();
            

            Console.WriteLine("\n\nENDE?");
            // END÷÷÷÷÷÷÷÷÷÷÷ /////////////////////////////////////////////////////////////////////

        }

        /// <summary>
        /// Handles Subscribe messages
        /// </summary>
        /// <param name="subscribe">the subscribe request</param>
        [ServiceHandler]
        public void SubscribeHandler(Subscribe subscribe)
        {
            SubscribeHelper(_submgrPort, subscribe.Body, subscribe.ResponsePort);
        }

        // ZEUCH //////////////////////////////////////////////////////////////////////////////////

        // Ports in a nutshell
        void testPort()
        {
            var port = new Port<string>();     // Port f¸r String-Instanzen 

            port.Post("Hallo"); // Einf¸gen // Anzahl der Elemente im Port 
            port.Post("Wald");
            port.Post("..oh, ich meine...");
            port.Post("Welt!");

            Console.WriteLine("Anzahl Items: " + port.ItemCount);

            string item;
            if (port.Test(out item))       // Eintrag mit Test abrufen 
                Console.Write(item + " ");
            else
                Console.WriteLine("port leer!");


            while (item != null)
            {
                item = port;        // so gehts auch mit implizitem cast
                Console.Write(item + " ");
            }
            Console.WriteLine();

        }

        // PortSets in a nutshell
        void testPortSet()
        {

            var genericPS = new PortSet<int, string>();                         // generisches PortSet
            genericPS.Post(1234);
            genericPS.Post("kleine");
            genericPS.Post("Schweinchen");

            String str = genericPS;     // str := "kleine"

            Console.WriteLine((int)genericPS + " " + genericPS.Test<string>());


            var runtimePS = new PortSet(typeof(int), typeof(string));      // runtime PortSet - nicht so schnell
            runtimePS.PostUnknownType(23);
            runtimePS.PostUnknownType("Ooompalooompas");

            Console.WriteLine(runtimePS.Test<int>() + " " + runtimePS.Test<string>());
        }

        // fuehrt task mit dem handler im erstbesten thread aus
        void testArbiter_FromHandler()
        {
            var dispatcher = new Dispatcher();
            var taskQueue = new DispatcherQueue("FromHandler_Dispatcher", dispatcher);

            Arbiter.Activate(
                taskQueue,
                Arbiter.FromHandler( () => Console.WriteLine("loift parallel"))
                );
        }


        // sequentielles Abarbeiten von Code, aufgeteilt in verschiedene Threads. psychoooooo O,o
        void testArbiter_FromIteratorHandler()
        {
            var dispatcher = new Dispatcher();
            var taskQueue = new DispatcherQueue("FromIteratorHandler_Dispatcher", dispatcher);

            Arbiter.Activate(
                taskQueue,                                      
                Arbiter.FromIteratorHandler(iteratorHandler /* handler-methode */)
                );
        }


        IEnumerator<ITask> iteratorHandler()
        {
            // yield ¸bergibt den returnten Wert und das Kommando sofort an den Aufrufer 
            // und kehrt danach zur Iteration zurueck - also kann ein ITask fuer den vorigen Wert
            // erstellt werden, *bevor* das Array komplett durchlaufen wurde


            // INFO: Port.Receive() Empfaengt ein Dingens vom Port, entfernt es jedoch _nicht_
            yield return globalDblPort.Receive();    // spucke ITask aus!

            //*** ab hier ist man in einem neuen Task, moeglicherweise in einem anderen thread ***
            
            Console.WriteLine("[iteratorHandler] dieses war der erste Streich, doch der zweite folgt sogleich!");


            yield return globalDblPort.Receive();    // spucke ITask aus!
            Console.WriteLine("[iteratorHandler] Task 2 laeuft :)");

            foreach (var d in globalDblArray)       // globalDblArray: [99,2,3,4]
            {
                // INFO: Port.Receive() Empfaengt ein Dingens vom Port, entfernt es jedoch _nicht_
                yield return globalDblPort.Receive();    // spucke ITask aus!   
                Console.WriteLine("[iteratorHandler] sagt: " + (double)globalDblPort);
            }
        }

        void testArbiter_SingleItemReceiver()
        {
            
            var dispatcher = new Dispatcher();
            var taskQueue = new DispatcherQueue("SIReceiver_Dispatcher", dispatcher);

            var port = new Port<int>();
            
            Arbiter.Activate(
                taskQueue,
                Arbiter.Receive(
                    false,                                          // soll Arbiter permanent am port lauschen?
                    port,                                           //Port 
                    arg => Console.WriteLine("Empfangen: " + arg))  // Handler 
                    );

            port.Post(13);
            port.Post(14);
        }


        void testArbiter_MultipleItemReceiver()
        {
            var dispatcher = new Dispatcher();
            var taskQueue = new DispatcherQueue("MIReceiver_Dispatcher", dispatcher);

            var port = new Port<int>();

            Arbiter.Activate(
                taskQueue,
                Arbiter.MultipleItemReceive( true, // mehr als ein mal aktiv?
                                             port,  // Port
                                             5,    // Auf wie viele Items willst du warten
                                             arr => Console.WriteLine("Empfangen: Array mit Laenge " + arr + ": {" + arr[0] + "," + arr[1] + "," + arr[2] + "," + arr[3] + "," + arr[4] + "}"))
                                            );

            port.Post(50); // 1. 5er-gruppe...
            port.Post(51); 
            port.Post(52);
            port.Post(53);
            port.Post(54);
            port.Post(55); // 2. 5er-gruppe...
            port.Post(56);
            port.Post(57);
            port.Post(58);
            port.Post(59);
            port.Post(60); // kommt nicht mehr an, weil in keiner 5er-Gruppe
        }

        void testArbiter_Interleave()
        {
            Dispatcher dispatcher = new Dispatcher();
            var taskQueue = new DispatcherQueue("Interleave_Dispatcher", dispatcher);

            var intPort = new Port<int>();
            var dblPort = new Port<double>();
            var strPort = new Port<string>();
            var boolPort = new Port<bool>();

            var portSet = new PortSet<int, double>(intPort, dblPort);

            Arbiter.Activate(
                taskQueue,
                Arbiter.Interleave(
                  new TeardownReceiverGroup(        // faehrt den Interleave bei Erhalt eines ints herunter
                      Arbiter.Receive( 
                        false,      // <-- muss false bei Teardown!
                        intPort, 
                        i => Console.WriteLine("Teardown: Shutting Down Interleave: " + i))),
                  new ExclusiveReceiverGroup(       // exclusive Ausfuehrung bei Erhalt eines doubles
                      Arbiter.Receive(
                        true,
                        dblPort,
                        d => Console.WriteLine("Exclusive: Empfangen: " + d))),  
                  new ConcurrentReceiverGroup(      // Exclusiv, aber die beiden inneren Receiver sind parallel!
                      Arbiter.Receive(
                        true,
                        strPort,
                        str => Console.WriteLine("Concurrent: Innerhalb des Concurrent Receivers parallel, nach aussen exclusiv: " + str)),
                    Arbiter.Receive(
                        true,
                        boolPort,
                        b => Console.WriteLine("Concurrent: Gurken sind keine Rudeltiere: " + b)))));
                      

            // Postings. Postings, die nach intPort.Post(14) (Teardown-Port)
            // noch in der Leitung sind (zeitlich, nicht von den Zeilen her!!!) , werden nicht beachtet ;)
            dblPort.Post(1);
            dblPort.Post(2);
            strPort.Post("HALLO");
            boolPort.Post(true);
            boolPort.Post(false);
            intPort.Post(14);
            strPort.Post("WELT!");  // geht idr nicht mehr, weil shutdown (es sei denn, er kommt vorher rein!)
        }

        void testArbiter_Join()
        {
            var dispatcher = new Dispatcher();
            var taskQueue = new DispatcherQueue("Join_Dispatcher", dispatcher);

            var intPort = new Port<int>();
            var dblPort = new Port<double>();

            // wird nur ausgefuert, wenn an beiden Ports empfangen werden kann
            Arbiter.Activate(
                taskQueue,
                Arbiter.JoinedReceive( true,    //persistent ? 
                                       intPort, //Port 1
                                       dblPort, //Port 2
                                       (i, d) => Console.WriteLine("summe von " + i + " + " + d + " ist " + (i + d))));

            intPort.Post(1);        // intPort: [1]     dblPort: []
            dblPort.Post(99);       // intport: [1]     dblPort: [99] --> join! 1, 99 werden aus queue entfernt
            intPort.Post(2);        // intport: [2]     dblPort: []
          //dblPort.Post(98);       
            intPort.Post(3);        // intport: [2,3]   dblPort: []
            dblPort.Post(97);       // intport: [2,3]   dblPort: [97] --> join! 2, 97 werden aus queue entfernt

        }

        // zeigt auch exclusives exception-handling
        void testArbtiter_Choice()
        {
            var dispatcher = new Dispatcher();
            var taskQueue = new DispatcherQueue("Choice_Dispatcher", dispatcher);

            var portSet = new PortSet<int, Exception>();

            // fuehrt einen der beiden Handler aus, je nachdem, an was ankommt
            // der Arbiter ist nicht persistent
            Arbiter.Activate(
                taskQueue,
                Arbiter.Choice(
                    portSet, //PortSet<int,Exception> 
                    i => Console.WriteLine("Got a " + i),   //int-Handler 
                    e => Console.WriteLine(e.Message)));    //exception-Handler

            portSet.Post(666);
            portSet.Post(new NullReferenceException("ERROR: all your base are belong to us"));

        }

        void testArbtiter_MultiPortReceive()
        {
            var dispatcher = new Dispatcher();
            var taskQueue = new DispatcherQueue("MultiPortReceive_Dispatcher", dispatcher);

            Port<int>[] portArr = { new Port<int>(), new Port<int>() };

            // Join fuer mehrere ports gleichen Typs
            Arbiter.Activate(
                taskQueue,
                Arbiter.MultiplePortReceive(
                    false, //persistent 
                    portArr,
                    arr => Console.WriteLine("Summe : " + (arr[0] + arr[1]) )));

            portArr[0].Post(1);
            portArr[1].Post(2);
        }


        // diese Methode sollte nicht mit Debuginformationen gestartet werden:
        // Strg + F5, damit die IDE nicht dazwischen funkt
        void testCausality()
        {
            var dispatcher = new Dispatcher();
            var taskQueue = new DispatcherQueue("testCausality_Dispatcher", dispatcher);

            var intPort = new Port<int>();
            var ePort = new Port<Exception>();
            
            var causality = new Causality("Causality_name", ePort); // (name, Exception-Port)
            Dispatcher.AddCausality(causality);                     // add causality to current thread

            // Kausalitaet wird ab nun jede unbehandelte Exception bearbeiten, 
            // die in *dieser* Methode, oder in funktionen, die direkt oder indirekt
            // durch messages aufgerufen wurden, die aus diesr methode kamen

            Arbiter.Activate(           // Fehlerbehandlungs-Arbiter
                taskQueue,
                Arbiter.Receive(
                    true,                     // persistent?
                    ePort,                    // port
                    delegate( Exception e)    // handling-funktion
                    {
                        Console.WriteLine( "[!!!!!!11einself] Der Handler meldet einen Fehler:\n" + e.Message);
                    }
                    ));    

            // ...

            Arbiter.Activate(           // Arbeits-Arbiter
                taskQueue,
                Arbiter.Receive(
                    false,             // persistent?
                    intPort,           // port to listen to
                    buggyHandler));    // handler

            intPort.Post(0);        // fehler ausloesen
        }

        void buggyHandler(int i)
        {
            int inverse = 1 / i;        // buggy code: i / 0 !
            Console.WriteLine("Das Reziproke von " + i + " ist " + inverse);
        }


        void SimpleCausalityExample()
        {
            Dispatcher dispatcher = new Dispatcher();
            DispatcherQueue _taskQueue;

            _taskQueue = new DispatcherQueue("global_Dispatcher", dispatcher);


            Port<Exception> exceptionPort = new Port<Exception>();

            // create a causality using the port instance
            Causality exampleCausality = new Causality("root cause", exceptionPort);

            // add causality to current thread
            Dispatcher.AddCausality(exampleCausality);

            // any unhandled exception from this point on, in this method or
            // any delegate that executes due to messages from this method,
            // will be posted on exceptionPort.

            Port<int> portInt = new Port<int>();
            Arbiter.Activate(_taskQueue,
                Arbiter.Receive(false, portInt, IntHandler)
            );

            // causalities flow when items are posted or Tasks are scheduled
            portInt.Post(0);

            // activate a handler on the exceptionPort
            // This is the failure handler for the causality
            Arbiter.Activate(_taskQueue,
                Arbiter.Receive(false, exceptionPort,
                delegate(Exception ex)
                {
                    // deal with failure here
                    Console.WriteLine(ex);
                })
            );
        }

        void IntHandler(int i)
        {
            // print active causalities
            foreach (Causality c in Dispatcher.ActiveCausalities)
            {
                Console.WriteLine(c.Name);
            }

            // expect DivideByZeroException that CCR will redirect to the causality
            int k = 10 / i;
        }


    } // END Class
} // END namespace


