import { WebPlugin } from '@capacitor/core';
import type { CapacitorSmsRetrieverPlugin } from './definitions';
export declare class CapacitorSmsRetrieverWeb extends WebPlugin implements CapacitorSmsRetrieverPlugin {
    getAppHash(): Promise<{
        hash: string;
    }>;
    startListening(): Promise<void>;
    stopListening(): Promise<void>;
}
declare const CapacitorSmsRetriever: CapacitorSmsRetrieverWeb;
export { CapacitorSmsRetriever };
