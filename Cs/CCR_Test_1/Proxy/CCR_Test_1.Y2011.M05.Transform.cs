//------------------------------------------------------------------------------
// <auto-generated>
//     Dieser Code wurde von einem Tool generiert.
//     Laufzeitversion:2.0.50727.5446
//
//     Änderungen an dieser Datei können falsches Verhalten verursachen und gehen verloren, wenn
//     der Code erneut generiert wird.
// </auto-generated>
//------------------------------------------------------------------------------

[assembly: global::System.Reflection.AssemblyVersionAttribute("1.0.0.0")]
[assembly: global::System.Reflection.AssemblyProductAttribute("CCR_Test_1")]
[assembly: global::System.Reflection.AssemblyTitleAttribute("CCR_Test_1")]
[assembly: global::Microsoft.Dss.Core.Attributes.ServiceDeclarationAttribute(global::Microsoft.Dss.Core.Attributes.DssServiceDeclaration.Transform, SourceAssemblyKey="CCR_Test_1.Y2011.M05, Version=1.0.0.0, Culture=neutral, PublicKeyToken=8f7d815ffc" +
    "392b39")]
[assembly: global::System.Security.SecurityTransparentAttribute()]
[assembly: global::System.Security.AllowPartiallyTrustedCallersAttribute()]

namespace Dss.Transforms.TransformCCR_Test_1 {
    
    
    public class Transforms : global::Microsoft.Dss.Core.Transforms.TransformBase {
        
        static Transforms() {
            Register();
        }
        
        public static void Register() {
            global::Microsoft.Dss.Core.Transforms.TransformBase.AddProxyTransform(typeof(global::CCR_Test_1.Proxy.CCR_Test_1State), new global::Microsoft.Dss.Core.Attributes.Transform(CCR_Test_1_Proxy_CCR_Test_1State_TO_CCR_Test_1_CCR_Test_1State));
            global::Microsoft.Dss.Core.Transforms.TransformBase.AddSourceTransform(typeof(global::CCR_Test_1.CCR_Test_1State), new global::Microsoft.Dss.Core.Attributes.Transform(CCR_Test_1_CCR_Test_1State_TO_CCR_Test_1_Proxy_CCR_Test_1State));
        }
        
        private static global::CCR_Test_1.Proxy.CCR_Test_1State _cachedInstance0 = new global::CCR_Test_1.Proxy.CCR_Test_1State();
        
        private static global::CCR_Test_1.CCR_Test_1State _cachedInstance = new global::CCR_Test_1.CCR_Test_1State();
        
        public static object CCR_Test_1_Proxy_CCR_Test_1State_TO_CCR_Test_1_CCR_Test_1State(object transformFrom) {
            return _cachedInstance;
        }
        
        public static object CCR_Test_1_CCR_Test_1State_TO_CCR_Test_1_Proxy_CCR_Test_1State(object transformFrom) {
            return _cachedInstance0;
        }
    }
}
