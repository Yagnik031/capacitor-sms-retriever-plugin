// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorSmsRetriever",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "CapacitorSmsRetriever",
            targets: ["CapacitorSmsRetrieverPluginPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "CapacitorSmsRetrieverPluginPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/CapacitorSmsRetrieverPluginPlugin"),
        .testTarget(
            name: "CapacitorSmsRetrieverPluginPluginTests",
            dependencies: ["CapacitorSmsRetrieverPluginPlugin"],
            path: "ios/Tests/CapacitorSmsRetrieverPluginPluginTests")
    ]
)