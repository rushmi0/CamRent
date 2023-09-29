import React from 'react';

const ProductDetail = () => {
    return (
        <>
            <div className="mx-auto">
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
                            <dt className="text-sm font-medium leading-6 text-gray-900 text-center">สัดส่วนภาพ (Image Ratio):</dt>
                            <dd className="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0 text-center">1:1, 3:2, 4:5, 16:9</dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 text-gray-900 text-center">พิกเซล (Effective Pixels):</dt>
                            <dd className="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0 text-center">45.7 Megapixel</dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 text-gray-900 text-center">เซลล์รับภาพ (Image Sensor):</dt>
                            <dd className="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0 text-center">Full frame (35.9 x 23.9 mm)/CMOS/Expeed 6</dd>
                        </div>
                    </dl>
                </div>
            </div>

        </>
    );
};

export default ProductDetail;
