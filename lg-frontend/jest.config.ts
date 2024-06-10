import type {Config} from 'jest';
import nextJest from 'next/jest.js';  

const createJestConfig = nextJest({
    dir: './',
})

const config: Config = {
    modulePaths: ["<rootDir>"],
    coverageProvider: "v8",
    testEnvironment: "jsdom",
    clearMocks: true,
    collectCoverage: true,
    coverageDirectory: "coverage",
    moduleNameMapper: {
        '^@/(.*)$': '<rootDir>/src/$1',
    },
};

export default createJestConfig(config);
