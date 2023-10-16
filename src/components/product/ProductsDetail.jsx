import React from 'react';

const ProductDetail = () => {
    return (
        <>
            <div className="container mx-auto">
                <div className="px-4 sm:px-0 pt-4 ml-7">
                    <h3 className="text-base font-semibold leading-7 text-gray-900">SPECIFICATION</h3>
                </div>
                <div className="mt-6 border-t border-gray-100">
                    <dl className="divide-y divide-gray-100">
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 text-gray-900 text-center">ความละเอียดภาพสูงสุด (Max Resolution):</dt>
                            <dd className="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0 text-center">8256 x 5504</dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 text-gray-900 text-center">Max Photo Resolution:</dt>
                            <dd className="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0 text-center">16.6 MP</dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 text-gray-900 text-center">Dimensions:</dt>
                            <dd className="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0 text-center">Approximately 64mm x 69mm x 24.38mm
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 text-gray-900 text-center">Weight:</dt>
                            <dd className="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0 text-center">Approximately 154g</dd>
                        </div>

                    </dl>
                </div>
            </div>

        </>
    );
};

export default ProductDetail;
