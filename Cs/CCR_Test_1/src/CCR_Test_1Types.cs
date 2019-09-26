using System;
using System.Collections.Generic;
using System.ComponentModel;
using Microsoft.Ccr.Core;
using Microsoft.Dss.Core.Attributes;
using Microsoft.Dss.ServiceModel.Dssp;
using Microsoft.Dss.ServiceModel.DsspServiceBase;
using W3C.Soap;

namespace CCR_Test_1
{

    /// <summary>
    /// CCR_Test_1 contract class
    /// </summary>
    public sealed class Contract                        // whoat ?!?!?!
    {
        /// <summary>
        /// DSS contract identifer for CCR_Test_1
        /// </summary>
        [DataMember]
        public const string Identifier = "http://schemas.tempuri.org/2011/05/ccr_test_1.html";
    }

    /// <summary>
    /// CCR_Test_1 state
    /// </summary>
    [DataContract]
    public class CCR_Test_1State
    {
    }

    /// <summary>
    /// CCR_Test_1 main operations port
    /// </summary>
    [ServicePort]
    public class CCR_Test_1Operations : PortSet<DsspDefaultLookup, DsspDefaultDrop, Get, Subscribe>
    {
    }

    /// <summary>
    /// CCR_Test_1 get operation
    /// </summary>
    public class Get : Get<GetRequestType, PortSet<CCR_Test_1State, Fault>>
    {
        /// <summary>
        /// Creates a new instance of Get
        /// </summary>
        public Get()
        {
        }

        /// <summary>
        /// Creates a new instance of Get
        /// </summary>
        /// <param name="body">the request message body</param>
        public Get(GetRequestType body)
            : base(body)
        {
        }

        /// <summary>
        /// Creates a new instance of Get
        /// </summary>
        /// <param name="body">the request message body</param>
        /// <param name="responsePort">the response port for the request</param>
        public Get(GetRequestType body, PortSet<CCR_Test_1State, Fault> responsePort)
            : base(body, responsePort)
        {
        }
    }

    /// <summary>
    /// CCR_Test_1 subscribe operation
    /// </summary>
    public class Subscribe : Subscribe<SubscribeRequestType, PortSet<SubscribeResponseType, Fault>>
    {
        /// <summary>
        /// Creates a new instance of Subscribe
        /// </summary>
        public Subscribe()
        {
        }

        /// <summary>
        /// Creates a new instance of Subscribe
        /// </summary>
        /// <param name="body">the request message body</param>
        public Subscribe(SubscribeRequestType body)
            : base(body)
        {
        }

        /// <summary>
        /// Creates a new instance of Subscribe
        /// </summary>
        /// <param name="body">the request message body</param>
        /// <param name="responsePort">the response port for the request</param>
        public Subscribe(SubscribeRequestType body, PortSet<SubscribeResponseType, Fault> responsePort)
            : base(body, responsePort)
        {
        }
    }
}


