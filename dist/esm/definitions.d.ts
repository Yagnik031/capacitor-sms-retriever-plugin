export interface CapacitorSmsRetrieverPlugin {
    getAppHash(): Promise<{
        hash: string;
    }>;
    startListening(): Promise<void>;
    stopListening(): Promise<void>;
}
