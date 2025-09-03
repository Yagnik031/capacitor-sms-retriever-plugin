import { WebPlugin } from '@capacitor/core';
export class CapacitorSmsRetrieverWeb extends WebPlugin {
    async getAppHash() {
        throw this.unimplemented('Not implemented on web.');
    }
    async startListening() {
        throw this.unimplemented('Not implemented on web.');
    }
    async stopListening() {
        throw this.unimplemented('Not implemented on web.');
    }
}
const CapacitorSmsRetriever = new CapacitorSmsRetrieverWeb();
export { CapacitorSmsRetriever };
//# sourceMappingURL=web.js.map