using System;
using System.Collections.Generic;
using Microsoft.Ccr.Core;

namespace CCR_SEMINAR2
{
    class MainClass
    {
        public static void Main(string[] agrs)
        {
            System.Threading.Thread.Sleep(1000);    // just for convenience

            var o = new MainClass();

            o.iteratorExample();

            //o.causalityExample();
        }

        internal void causalityExample()
        {
            var port = new Port<int>();
            var excPort = new Port<Exception>();
            var causality = new Causality("causality name", excPort);

            var dispatcher = new Dispatcher(10, "Hallo");
            var taskQueue = new DispatcherQueue("dispatcher name", dispatcher);

            Arbiter.Activate(       // Arbiter koordiniert Ausnahmehandler
                taskQueue,
                Arbiter.Receive(
                    false,
                    excPort,
                    exc => Console.WriteLine(exc.Message)
                ));

            Dispatcher.AddCausality(causality); // füge Kausalität zu zum Kontext hinzu


            Arbiter.Activate(
                taskQueue,
                Arbiter.Receive(
                    false,
                    port,
                    delegate(int i)
                    {
                        throw new NullReferenceException("ERROR");
                    }
                ));

            port.Post(0); // löse Ausnahme aus
        }


        void iteratorExample()
        {
            var dispatcher = new Dispatcher();
            var taskQueue = new DispatcherQueue("dispatcher name", dispatcher);

            Arbiter.Activate(
                taskQueue,
                Arbiter.FromIteratorHandler(iteratorHandler)
                );
        }

        IEnumerator<ITask> iteratorHandler()
        {
            var port = new Port<int>();
            
            for (int i = 0; i < 10000; i++)
                port.Post(i);

            yield return port.Receive(i => Console.WriteLine("Received stage 1: " + i));   // generiert ITask!
            yield return port.Receive(i => Console.WriteLine("Received stage 2: " + i));

            ITask task = port.Receive(i => Console.WriteLine("Received stage 3.0: " + i));
            while(task != null)
            {
                yield return task;

                task = port.Receive(i => Console.WriteLine("Received stage 3.1: " + i));
            }
        }
    }
}