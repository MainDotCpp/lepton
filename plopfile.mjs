const moduleMapping = {
    framework: {
        name: 'system',
        package: 'com.leuan.lepton',
        module: 'lepton-module-framework',
        dir: 'src/main/kotlin/com/leuan/lepton'
    },
    customer: {
        name: 'customer',
        package: 'com.leuan.lepton.customer',
        module: 'lepton-module-customer',
        dir: 'src/main/kotlin/com/leuan/lepton/customer'
    }
}
export default function Plopfile(plop) {
    plop.setGenerator('pojo', {
            description: 'application controller logic',
            prompts: [
                {
                    type: 'list',
                    name: 'module',
                    message: '模块',
                    choices: Object.keys(moduleMapping)
                },
                {
                    type: 'input',
                    name: 'biz',
                    message: '请输入业务名称(例如:user)'
                },
                {
                    type: 'input',
                    name: 'comment',
                    message: '请输入业务注释'
                }
            ],
            actions: (data) => {
                console.log(`plop ${data.module} ${data.biz} ${data.comment}`)
                data.module = moduleMapping[data.module]
                return [
                    {
                        type: 'addMany',
                        base: '_templates/service',
                        destination: 'gen/{{module.module}}/{{lowerCase module.dir}}',
                        force: true,
                        templateFiles: '_templates/service',
                        data: {
                            basePackage: 'com.leuan.lepton',
                        }
                    },
                    // {
                    //     type: 'append',
                    //     templateFile: '_templates/permission.sql.hbs',
                    //     unique: false,
                    //     path: 'gen/permission.sql'
                    // },
                ]
            }
        }
    )
}