import { registerPlugin } from '@capacitor/core';
const CapacitorSmsRetrieverPlugin = registerPlugin('CapacitorSmsRetrieverPlugin', {
    web: () => import('./web').then(m => new m.CapacitorSmsRetrieverWeb()),
});
export * from './definitions';
//# sourceMappingURL=index.js.map