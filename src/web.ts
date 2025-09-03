import { WebPlugin } from '@capacitor/core';
import type { CapacitorSmsRetrieverPlugin } from './definitions';

export class CapacitorSmsRetrieverWeb extends WebPlugin implements CapacitorSmsRetrieverPlugin {
  async getAppHash(): Promise<{ hash: string }> {
    throw this.unimplemented('Not implemented on web.');
  }
  async startListening(): Promise<void> {
    throw this.unimplemented('Not implemented on web.');
  }
  async stopListening(): Promise<void> {
    throw this.unimplemented('Not implemented on web.');
  }
}

const CapacitorSmsRetriever = new CapacitorSmsRetrieverWeb();

export { CapacitorSmsRetriever };
