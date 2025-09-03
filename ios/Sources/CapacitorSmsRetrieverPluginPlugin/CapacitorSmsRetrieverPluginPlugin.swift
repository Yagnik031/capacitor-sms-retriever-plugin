import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapacitorSmsRetrieverPluginPlugin)
public class CapacitorSmsRetrieverPluginPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "CapacitorSmsRetrieverPluginPlugin"
    public let jsName = "CapacitorSmsRetrieverPlugin"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = CapacitorSmsRetrieverPlugin()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
}
