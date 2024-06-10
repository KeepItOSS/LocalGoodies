/** @type {import('next').NextConfig} */
const nextConfig = {
    async rewrites() {
        return [
            {
                source: '/api/business-listing/:path*',
                destination: 'http://localhost:8080/api/business-listing/:path*',
            }
        ];
    }
};
export default nextConfig;
