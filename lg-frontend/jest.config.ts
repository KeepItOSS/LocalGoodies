import type {Config} from 'jest';

const config: Config = {
    coverageProvider: "v8",
    testEnvironment: "jsdom",

    clearMocks: true,
    collectCoverage: true,
    coverageDirectory: "coverage",
};

export default config;
